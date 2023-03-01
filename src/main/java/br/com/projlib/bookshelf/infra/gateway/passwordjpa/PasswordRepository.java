package br.com.projlib.bookshelf.infra.gateway.passwordjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<PasswordJpa, Long> {
}
