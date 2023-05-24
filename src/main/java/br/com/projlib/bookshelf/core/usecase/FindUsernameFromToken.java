package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.TokenGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUsernameFromToken {
    private final TokenGateway tokenGateway;
    public String process(String token) {
        return tokenGateway.getUsernameFromToken(token);
    }
}
