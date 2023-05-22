package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.BorrowingGateway;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllBorrowings {
    private final BorrowingGateway borrowingGateway;

    public List<BorrowingJpa> process() {
        List<BorrowingJpa> all = borrowingGateway.getAll();
        return all;
    }
}
