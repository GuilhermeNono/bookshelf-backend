package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface BorrowingGateway {
    List<BorrowingJpa> findAll();

    Page<BorrowingJpa> findAll(Specification<BorrowingJpa> spec, Pageable pageable);

    Optional<BorrowingJpa> findById(long id);

    void save(BorrowingJpa borrowing);
}

