package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.UserAccountGateway;
import br.com.projlib.bookshelf.infra.gateway.syspermissionjpa.SysPermissionJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class FindAuthoritiesByAuthenticatedUser {
    private final UserAccountGateway userAccountGateway;

    public Collection<SysPermissionJpa> process() {
        return userAccountGateway.findAuthoritiesByAuthenticatedUser();
    }
}
