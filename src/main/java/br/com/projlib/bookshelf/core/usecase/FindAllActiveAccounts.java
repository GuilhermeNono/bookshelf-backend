package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.core.gateway.UserAccountGateway;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllActiveAccounts {
    private final UserAccountGateway userAccountGateway;

    public List<UserAccount> process(){
        return userAccountGateway.findAllActiveAccounts().stream().map(UserAccountJpa::toDomain)
                .toList();
    }
}
