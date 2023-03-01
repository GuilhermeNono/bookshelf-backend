package br.com.projlib.bookshelf.infra.gateway.authorjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorJpa, Long> {
}
