package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.BookGateway;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindBookByName {
    private final BookGateway bookGateway;

    public List<BookJpa> process(String name) {
        return bookGateway.findByName(name);
    }
}
