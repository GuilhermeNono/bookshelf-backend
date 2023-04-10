package br.com.projlib.bookshelf.infra.gateway.usercontact;

import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserContactRepository extends JpaRepository<UserContactJpa, Long> {
    List<UserContactJpa> findAllByUserAccountAndActiveIsTrue(final UserAccountJpa userAccountJpa);
}
