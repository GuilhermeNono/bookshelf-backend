package br.com.projlib.bookshelf.infra.gateway.categoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryJpa, Long> {
}
