package br.com.projlib.bookshelf.infra.gateway.bookjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookJpa, Long> {
}
