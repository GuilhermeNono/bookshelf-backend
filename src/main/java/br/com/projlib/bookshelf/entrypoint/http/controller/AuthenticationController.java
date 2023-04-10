package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.Authenticate;
import br.com.projlib.bookshelf.core.usecase.BuildToken;
import br.com.projlib.bookshelf.core.usecase.FindAuthoritiesByAuthenticatedUser;
import br.com.projlib.bookshelf.core.usecase.GetAuthenticatedUserAccount;
import br.com.projlib.bookshelf.core.usecase.ValidateAuthToken;
import br.com.projlib.bookshelf.entrypoint.http.response.UserPermissionsResponse;
import br.com.projlib.bookshelf.infra.command.AuthenticationToken;
import br.com.projlib.bookshelf.infra.command.LoginCommand;
import br.com.projlib.bookshelf.infra.gateway.syspermissionjpa.SysPermissionJpa;
import br.com.projlib.bookshelf.infra.query.UserAccountQuery;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/authentication")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController implements Serializable {

    private final Authenticate authenticate;
    private final BuildToken buildToken;
    private final ValidateAuthToken validateAuthToken;
    private final GetAuthenticatedUserAccount getAuthenticatedUserAccount;
    private final FindAuthoritiesByAuthenticatedUser findAuthoritiesByAuthenticatedUser;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationToken> login(@RequestBody @Valid LoginCommand loginCommand) {
        this.authenticate.process(loginCommand.getEmail(), loginCommand.getPassword());

        return ResponseEntity.ok(this.buildToken.process(loginCommand.getEmail()));
    }

    @PostMapping(value = "/validate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> validate(@RequestBody AuthenticationToken token) {
        this.validateAuthToken.process(token);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "me", produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserAccountQuery> getAuthenticatedUser() {
        return ResponseEntity.ok(this.getAuthenticatedUserAccount.process());
    }
    //
    @GetMapping(value = "/me/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserPermissionsResponse> getAuthorizations() {
        final Collection<SysPermissionJpa> permissions = this.findAuthoritiesByAuthenticatedUser.process();

        return ResponseEntity.ok(
                new UserPermissionsResponse(
                        permissions.parallelStream()
                                .map(SysPermissionJpa::getCode)
                                .collect(Collectors.toList())
                )
        );
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserAccountQuery> findByUsername(@PathParam("username") String username) {
//        return ResponseEntity.ok(this.userAccountService
//                .findByUsername(username)
//                .map(this.userAccountQueryAssembler::toModel)
//                .orElseThrow(() -> new EntityNotFoundException(UserAccount.class)));
//    }
}
