package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.BorrowingGateway;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBorrowingById {
    private final BorrowingGateway borrowingGateway;

    public BorrowingJpa process(long id) {
        return borrowingGateway.findById(id).orElseThrow();
    }
}
