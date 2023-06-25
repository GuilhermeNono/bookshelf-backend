package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.CreateUserAccount;
import br.com.projlib.bookshelf.core.usecase.FindAllUser;
import br.com.projlib.bookshelf.core.usecase.FindUserById;
import br.com.projlib.bookshelf.entrypoint.http.request.UserAccountCreateRequest;
import br.com.projlib.bookshelf.entrypoint.http.response.UserAccountResponse;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Account")
@Slf4j
public class UserAccountController {

    private final ModelMapper modelMapper;

    private final FindAllUser findAllUser;
    private final CreateUserAccount createUserAccount;
    private final FindUserById findUserById;

    @Operation(summary = "Get all users")
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<UserAccountResponse>> getAll() {
        try {
            List<UserAccountResponse> responses = findAllUser.process()
                    .stream()
                    .map(user -> modelMapper.map(user, UserAccountResponse.class))
                    .toList();

            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get a user by id")
    @GetMapping(value = "/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserAccountResponse> getOne(@PathVariable final long id) {
        try {
            UserAccountResponse responses = modelMapper.map(findUserById.process(id), UserAccountResponse.class);

            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new user account")
    @PostMapping
    public ResponseEntity<UserAccountResponse> createUser(@RequestBody @Valid UserAccountCreateRequest userAccountCreateRequest) {
        try {
            UserAccountJpa userAccount = createUserAccount.process(userAccountCreateRequest);
            UserAccountResponse response = modelMapper.map(userAccount, UserAccountResponse.class);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
}
