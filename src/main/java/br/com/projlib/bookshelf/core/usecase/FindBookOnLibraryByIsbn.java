package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.BookCopyGateway;
import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindBookOnLibraryByIsbn {
    private final BookCopyGateway bookCopyGateway;

    public List<BookCopyJpa> process(String isbn, long id){
        return bookCopyGateway.findBooksOfLibraryByIsbn(isbn, id);
    }
}
