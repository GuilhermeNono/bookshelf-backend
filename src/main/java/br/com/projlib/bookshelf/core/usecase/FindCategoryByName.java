package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.CategoryGateway;
import br.com.projlib.bookshelf.infra.gateway.categoryjpa.CategoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindCategoryByName {
    private final CategoryGateway categoryGateway;

    public CategoryJpa process(String name) {
        return categoryGateway.findByName(name);
    }
}
