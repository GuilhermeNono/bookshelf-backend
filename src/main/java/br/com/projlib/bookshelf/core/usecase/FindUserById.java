package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.UserAccountGateway;
import br.com.projlib.bookshelf.infra.exception.UserNotFoundException;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserById {
    private final UserAccountGateway userAccountGateway;

    public UserAccountJpa process(long id) {
        return userAccountGateway.findUserById(id).orElseThrow(UserNotFoundException::new);
    }
}
