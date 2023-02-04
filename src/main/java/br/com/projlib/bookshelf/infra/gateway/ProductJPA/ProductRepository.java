package br.com.projlib.bookshelf.infra.gateway.ProductJPA;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductJpa, Long> {}
