package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;

import java.util.List;

public interface BorrowingGateway {
    List<BorrowingJpa> getAll();
}
