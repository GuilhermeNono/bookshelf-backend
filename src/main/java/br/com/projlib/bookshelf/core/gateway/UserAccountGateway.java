package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.syspermissionjpa.SysPermissionJpa;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.query.UserAccountQuery;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserAccountGateway {

    List<UserAccountJpa> findAll();

    Optional<UserAccountJpa> findUserByCpf(final String cpf);

    Optional<UserAccountJpa> findUserByEmail(final String email);

    List<UserAccountJpa> findAllActiveAccounts();

    Optional<UserAccountJpa> findUserById(final long id);

    UserAccountJpa create(final UserAccountJpa userAccount);

    Collection<SysPermissionJpa> findAuthoritiesByUser(final UserDetails userDetails);

    Collection<SysPermissionJpa> findAuthoritiesByAuthenticatedUser();

    UserAccountQuery getAuthenticatedUserAccount();

    UserAccountJpa loadAuthenticatedUserAccount();


}
