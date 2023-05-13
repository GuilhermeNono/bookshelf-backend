package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;

import java.util.List;
import java.util.Optional;

public interface BookGateway {

    List<BookJpa> findAll();

    List<BookJpa> findByName(String name);

    List<BookJpa> findByIsbn(String isbn);

    Optional<BookJpa> findById(final long id);

}
