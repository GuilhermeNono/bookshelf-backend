package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface BookGateway {

    List<BookJpa> findAll();

    Page<BookJpa> findAll(Specification<BookJpa> spec, Pageable pageable);

    List<BookJpa> findByName(String name);

    List<BookJpa> findByIsbn(String isbn);

    Optional<BookJpa> findById(final long id);

    BookJpa save(BookJpa book);
}
