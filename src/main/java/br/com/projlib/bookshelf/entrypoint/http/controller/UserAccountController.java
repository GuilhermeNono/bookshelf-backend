package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindUserById;
import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.core.usecase.CreateUser;
import br.com.projlib.bookshelf.core.usecase.FindAllUser;
import br.com.projlib.bookshelf.core.usecase.FindProfileById;
import br.com.projlib.bookshelf.entrypoint.http.request.UserAccountRequest;
import br.com.projlib.bookshelf.entrypoint.http.response.UserAccountResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Account")
public class UserAccountController {

    private final FindAllUser findAllUser;
    private final CreateUser createUser;
    private final FindProfileById findProfileById;
    private final FindUserById findUserById;

    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<UserAccountResponse>> getAll() {
        final List<UserAccountResponse> responses = findAllUser.process().stream()
                .map(UserAccountResponse::fromDomain).toList();

        return ResponseEntity.ok(responses);
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserAccountResponse> create(@RequestBody @Valid UserAccountRequest userAccountRequest) {
        final UserAccount userAccount = userAccountRequest.toDomain();
        final UserAccountResponse response = UserAccountResponse.fromDomain(createUser.process(userAccount));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
