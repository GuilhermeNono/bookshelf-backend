package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.BookGateway;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBookById {
    private final BookGateway bookGateway;

    public BookJpa process(long id) {
        return bookGateway.findById(id).orElseThrow(RuntimeException::new);
    }
}
