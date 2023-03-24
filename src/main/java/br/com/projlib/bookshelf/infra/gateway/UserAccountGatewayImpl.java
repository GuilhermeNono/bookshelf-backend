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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAccountGatewayImpl implements UserAccountGateway, UserDetailsService, Serializable {

    private final UserAccountRepository userAccountRepository;
    private final SysPermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userAccountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    @Transactional
    public List<UserAccount> findAll() {
        return userAccountRepository.findAll()
                .stream()
                .map(UserAccountJpa::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserAccount> findAllActiveAccounts() {
        return userAccountRepository
                .findAllByActiveIsTrue()
                .stream().map(UserAccountJpa::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public UserAccount findUserById(long id) {
        return userAccountRepository
                .findById(id).orElseThrow().toDomain();
    }

    @Override
    @Transactional
    public UserAccount create(UserAccount userAccount) {
        return userAccountRepository.save(UserAccountJpa.fromDomain(userAccount)).toDomain();
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
        UserAccountQuery userAccountQuery = new UserAccountQuery(userAccount.getUsername(), userAccount.getUserProfile().getPersonName(), userAccount.isActive(), userAccount.getUserProfile().getId());
        userAccountQuery.setId(userAccount.getId());

        return userAccountQuery;
    }

    @Override
    public UserAccountJpa loadAuthenticatedUserAccount() {
        UserDetails userDetails = AuthenticationGatewayImpl.getLoggedUser().orElseThrow(RuntimeException::new);

        return this.userAccountRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Load authenticated User error"));

    }


//    @Override
//    public Page<UserAccountJpa> findByQuery(QueryCriteria query) {
//        return userAccountRepository.findAll(this.buildSpecification(query), query.getPageable());
//    }

//    private Specification<UserAccountJpa> buildSpecification(final QueryCriteria query) {
//        Specification<UserAccountJpa> specification = Specification.not(null);
//
//        if (Objects.nonNull(query) && Objects.nonNull(query.getFilter())) {
//            final UserAccountFilter filter = (UserAccountFilter) query.getFilter();
//
//            if (Objects.nonNull(filter.getUsername())) {
//                specification = specification.and(UserAccountSpecification.usernameEqualsIgnoreCase(filter.getUsername()));
//            }
//        }
//
//        return specification;
//    }
}
