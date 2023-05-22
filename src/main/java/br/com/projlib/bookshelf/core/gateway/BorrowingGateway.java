package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BorrowingGateway {
    List<BorrowingJpa> findAll();

    Page<BorrowingJpa> findAll(Specification<BorrowingJpa> spec, Pageable pageable);

    List<BorrowingJpa> getAll();
}

