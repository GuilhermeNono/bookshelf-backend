package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.authorjpa.AuthorJpa;

import java.util.Optional;

public interface AuthorGateway {
    Optional<AuthorJpa> findById(long id);

    AuthorJpa save(AuthorJpa authorJpa);

    AuthorJpa findByFullName(String firstName, String lastName);
}
