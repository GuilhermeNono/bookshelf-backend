package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.infra.gateway.UserAccountGatewayImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadUserByUsername {

    private final UserAccountGatewayImpl userAccountGateway;

    public UserDetails process(String username){
        return userAccountGateway.loadUserByUsername(username);
    }

}
