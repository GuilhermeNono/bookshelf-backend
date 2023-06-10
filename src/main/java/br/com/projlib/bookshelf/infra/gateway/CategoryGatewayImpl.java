package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.CategoryGateway;
import br.com.projlib.bookshelf.infra.gateway.categoryjpa.CategoryJpa;
import br.com.projlib.bookshelf.infra.gateway.categoryjpa.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryGatewayImpl implements CategoryGateway  {

    private final CategoryRepository categoryRepository;

    @Override
    public Optional<CategoryJpa> findById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    @Transactional
    public CategoryJpa save(CategoryJpa categoryJpa) {
        return categoryRepository.save(categoryJpa);
    }

    @Override
    public CategoryJpa findByName(String name) {
        return categoryRepository.findCategoryJpaByName(name);
    }
}
