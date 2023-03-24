package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.AuthenticationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Authenticate {
    private final AuthenticationGateway authenticationGateway;

    public void process(String username, String password){
        authenticationGateway.authenticate(username, password);
    }
}
