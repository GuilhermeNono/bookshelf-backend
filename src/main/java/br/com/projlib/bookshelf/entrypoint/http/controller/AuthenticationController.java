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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import static br.com.projlib.bookshelf.infra.config.AuthenticationFilter.TOKEN_BEARER;

@RestController
@RequestMapping(value = "/api/v1/authentication")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication")
public class AuthenticationController implements Serializable {

    private final Authenticate authenticate;
    private final BuildToken buildToken;
    private final ValidateAuthToken validateAuthToken;
    private final GetAuthenticatedUserAccount getAuthenticatedUserAccount;
    private final FindAuthoritiesByAuthenticatedUser findAuthoritiesByAuthenticatedUser;


    @Operation(summary = "Authenticate user and Get access token")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationToken> login(@RequestBody @Valid LoginCommand loginCommand) {
        this.authenticate.process(loginCommand.getEmail(), loginCommand.getPassword());

        return ResponseEntity.ok(this.buildToken.process(loginCommand.getEmail()));
    }

    @Operation(summary = "Validate token")
    @PostMapping(value = "/validate")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> validate(@RequestHeader(value = "Authorization") String authToken) {
            String token = authToken.substring(TOKEN_BEARER.length());
            this.validateAuthToken.process(new AuthenticationToken(token));
            return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get authenticated user informations")
    @GetMapping(value = "me", produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserAccountQuery> getAuthenticatedUser() {
        return ResponseEntity.ok(this.getAuthenticatedUserAccount.process());
    }

    @Operation(summary = "Get authenticated user permissions")
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
}
