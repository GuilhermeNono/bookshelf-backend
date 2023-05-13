package br.com.projlib.bookshelf.infra.gateway.bookjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookJpa, Long> {

    @Query("select b from BookJpa b where b.name like ?1")
    List<BookJpa> findByName(String name);

    @Query("select b from BookJpa b where b.isbn like ?1")
    List<BookJpa> findByIsbn(String isbn);
}
