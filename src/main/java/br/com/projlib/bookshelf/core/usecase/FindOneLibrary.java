package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.LibraryGateway;
import br.com.projlib.bookshelf.infra.exception.LibraryNotFoundException;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindOneLibrary {
    private final LibraryGateway libraryGateway;

    public LibraryJpa process(final long id) {
            return libraryGateway.getOne(id)
                    .orElseThrow(LibraryNotFoundException::new);
    }
}
