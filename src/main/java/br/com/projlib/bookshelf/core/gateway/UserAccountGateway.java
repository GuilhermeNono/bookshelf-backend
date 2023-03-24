package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.infra.gateway.syspermissionjpa.SysPermissionJpa;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.query.UserAccountQuery;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public interface UserAccountGateway {

    List<UserAccount> findAll();

    List<UserAccount> findAllActiveAccounts();

    UserAccount findUserById(final long id);

    UserAccount create(UserAccount userAccount);

    Collection<SysPermissionJpa> findAuthoritiesByUser(UserDetails userDetails);

    Collection<SysPermissionJpa> findAuthoritiesByAuthenticatedUser();

    UserAccountQuery getAuthenticatedUserAccount();

    UserAccountJpa loadAuthenticatedUserAccount();
}
