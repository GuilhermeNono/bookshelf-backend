package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.infra.gateway.syspermissionjpa.SysPermissionJpa;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.query.UserAccountQuery;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserAccountGateway {

    List<UserAccountJpa> findAll();

    Optional<UserAccountJpa> findUserByCpf(String cpf);


    Optional<UserAccountJpa> findUserByEmail(String email);

    List<UserAccountJpa> findAllActiveAccounts();

    UserAccountJpa findUserById(final long id);

    UserAccountJpa create(UserAccountJpa userAccount);

    Collection<SysPermissionJpa> findAuthoritiesByUser(UserDetails userDetails);

    Collection<SysPermissionJpa> findAuthoritiesByAuthenticatedUser();

    UserAccountQuery getAuthenticatedUserAccount();

    UserAccountJpa loadAuthenticatedUserAccount();
}
