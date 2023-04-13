package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.domain.UserAccount;
import br.com.projlib.bookshelf.core.gateway.UserAccountGateway;
import br.com.projlib.bookshelf.infra.gateway.syspermissionjpa.SysPermissionJpa;
import br.com.projlib.bookshelf.infra.gateway.syspermissionjpa.SysPermissionRepository;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountRepository;
import br.com.projlib.bookshelf.infra.query.UserAccountQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAccountGatewayImpl implements UserAccountGateway, UserDetailsService, Serializable {

    private final UserAccountRepository userAccountRepository;
    private final SysPermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //TODO: Refazer a Exception para EmailNotFoundException
        return this.userAccountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public List<UserAccountJpa> findAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public Optional<UserAccountJpa> findUserByCpf(String cpf) {
        return userAccountRepository.findByCpf(cpf);
    }

    @Override
    public Optional<UserAccountJpa> findUserByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }

    @Override
    public List<UserAccountJpa> findAllActiveAccounts() {
        return userAccountRepository
                .findAllByActiveIsTrue();

    }

    @Override
    public UserAccountJpa findUserById(long id) {
        return userAccountRepository
                .findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public UserAccountJpa create(UserAccountJpa userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Override
    public Collection<SysPermissionJpa> findAuthoritiesByUser(UserDetails userDetails) {
        if (userDetails instanceof UserAccountJpa) {
            return this.permissionRepository.findByProfileId(((UserAccountJpa) userDetails).getUserProfile().getId());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Collection<SysPermissionJpa> findAuthoritiesByAuthenticatedUser() {
        final UserAccountJpa userAccount = this.loadAuthenticatedUserAccount();

        return this.findAuthoritiesByUser(userAccount);
    }

    @Override
    public UserAccountQuery getAuthenticatedUserAccount() {
        UserAccountJpa userAccount = this.loadAuthenticatedUserAccount();
        UserAccountQuery userAccountQuery = new UserAccountQuery(userAccount.getUsername(), userAccount.getPersonName(), userAccount.isActive(), userAccount.getUserProfile().getId());
        userAccountQuery.setId(userAccount.getId());

        return userAccountQuery;
    }

    @Override
    public UserAccountJpa loadAuthenticatedUserAccount() {
        UserDetails userDetails = AuthenticationGatewayImpl.getLoggedUser().orElseThrow(RuntimeException::new);
        //TODO: O getUsername a partir de agora deverá ser o CPF

        return this.userAccountRepository.findByCpf(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Load authenticated User error"));

    }
}
