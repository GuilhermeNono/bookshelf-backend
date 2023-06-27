package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.BookCopyGateway;
import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoveBookCopy {

    private final BookCopyGateway bookCopyGateway;

    public void process(BookCopyJpa bookCopy) {
        bookCopyGateway.remove(bookCopy);
    }

}
