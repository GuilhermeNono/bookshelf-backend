package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;

import java.util.List;
import java.util.Optional;

public interface BookGateway {

    List<BookJpa> findAll();

    Optional<BookJpa> findById(final long id);

}
