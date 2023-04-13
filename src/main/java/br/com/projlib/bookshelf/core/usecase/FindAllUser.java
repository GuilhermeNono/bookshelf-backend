package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.core.gateway.UserAccountGateway;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllUser {

    private final UserAccountGateway userAccountGateway;

    public Collection<UserAccount> process() {
        return userAccountGateway.findAll()
                .stream()
                .map(UserAccountJpa::toDomain)
                .collect(Collectors.toList());
    }

}
