package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.UserAccountGateway;
import br.com.projlib.bookshelf.infra.query.UserAccountQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAuthenticatedUserAccount {
    private final UserAccountGateway userAccountGateway;

    public UserAccountQuery process() {
        return userAccountGateway.getAuthenticatedUserAccount();
    }
}
