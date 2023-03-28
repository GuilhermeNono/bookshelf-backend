package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.TokenGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenerateToken {
    private final TokenGateway tokenGateway;

    public String process(UserDetails userDetails){
        return tokenGateway.generateToken(userDetails);
    }
}
