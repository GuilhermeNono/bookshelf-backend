package br.com.projlib.bookshelf.infra.gateway.useraccountjpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccountJpa, Long> {
    List<UserAccountJpa> findAllByActiveIsTrue();

    Optional<UserAccountJpa> findByUsername(final String username);

}
