package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.publisherjpa.PublisherJpa;

import java.util.Optional;

public interface PublisherGateway {
    Optional<PublisherJpa> findById(long id);

    PublisherJpa save(PublisherJpa publisherJpa);

    PublisherJpa findByName(String name);
}
