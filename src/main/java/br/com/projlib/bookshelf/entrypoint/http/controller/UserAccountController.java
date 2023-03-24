package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.core.usecase.CreateUser;
import br.com.projlib.bookshelf.core.usecase.FindAllUser;
import br.com.projlib.bookshelf.entrypoint.http.response.UserAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserAccountController {

    private final FindAllUser findAllUser;
    private final CreateUser createUser;

    @GetMapping
    public ResponseEntity<List<UserAccountResponse>> getAll() {
        final List<UserAccountResponse> responses = findAllUser.process().stream()
                .map(UserAccountResponse::fromDomain).toList();

        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<UserAccountResponse> create(@RequestBody UserAccountRequest userAccountRequest) {
        final UserAccount userAccount = userAccountRequest.toDomain();
        final UserAccountResponse response = UserAccountResponse.fromDomain(createUser.process(userAccount));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
