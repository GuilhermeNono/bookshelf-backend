package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.BookCopyGateway;
import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllBooksOfLibrary {
    private final BookCopyGateway bookCopyGateway;

    public List<BookCopyJpa> process(long id) {
        return bookCopyGateway.findAllBooksOfLibrary(id);
    }
}
