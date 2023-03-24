package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.AuthenticationGateway;
import br.com.projlib.bookshelf.infra.command.AuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateAuthToken {
    private final AuthenticationGateway authenticationGateway;

    public void process(AuthenticationToken token) {
        this.authenticationGateway.validateToken(token);
    }
}
