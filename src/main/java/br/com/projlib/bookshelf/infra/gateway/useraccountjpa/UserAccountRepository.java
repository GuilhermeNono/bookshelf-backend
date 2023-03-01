package br.com.projlib.bookshelf.infra.gateway.useraccountjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccountJpa, Long> {
}
