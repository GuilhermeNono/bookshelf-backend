package br.com.projlib.bookshelf.infra.gateway.bookcopyjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCopyRepository extends JpaRepository<BookCopyJpa, Long> {
}
