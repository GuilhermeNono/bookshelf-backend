package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.BorrowingGateway;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveBorrowing {
    private final BorrowingGateway borrowingGateway;

    public void process(BorrowingJpa borrowingJpa) {
        borrowingGateway.create(borrowingJpa);
    }
}
