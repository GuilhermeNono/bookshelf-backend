package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;

import java.util.List;

public interface BookGateway {

    List<BookJpa> findAll();

}
