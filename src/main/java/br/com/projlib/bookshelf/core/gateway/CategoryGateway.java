package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.categoryjpa.CategoryJpa;

import java.util.Optional;

public interface CategoryGateway {
    Optional<CategoryJpa> findById(long id);

    CategoryJpa save(CategoryJpa categoryJpa);

    CategoryJpa findByName(String name);
}
