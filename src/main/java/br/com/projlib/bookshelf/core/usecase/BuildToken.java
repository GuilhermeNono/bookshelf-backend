package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.AuthenticationGateway;
import br.com.projlib.bookshelf.infra.command.AuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildToken {
    private final AuthenticationGateway authenticationGateway;

    public AuthenticationToken process(String username) {
        return this.authenticationGateway.buildToken(username);
    }

}
