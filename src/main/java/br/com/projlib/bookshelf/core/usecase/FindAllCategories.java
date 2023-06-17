package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.CategoryGateway;
import br.com.projlib.bookshelf.infra.gateway.categoryjpa.CategoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllCategories {
    private final CategoryGateway categoryGateway;

    public List<CategoryJpa> process() {
        return categoryGateway.findAll();
    }
}
