package br.com.projlib.bookshelf.infra.gateway.libraryjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<LibraryJpa, Long> {
}
