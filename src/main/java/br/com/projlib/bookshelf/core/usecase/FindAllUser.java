package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.core.gateway.UserAccountGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class FindAllUser {

    private UserAccountGateway userAccountGateway;

    public Collection<UserAccount> process() {
        return userAccountGateway.findAll();
    }

}
