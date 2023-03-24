package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.TokenGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateToken {
    private final TokenGateway tokenGateway;

    public boolean process(String token, UserDetails userDetails) {
        return this.tokenGateway.validateToken(token, userDetails);
    }
}
