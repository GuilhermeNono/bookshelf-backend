package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.BookCopyGateway;
import br.com.projlib.bookshelf.core.usecase.GetOneLibrary;
import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyRepository;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCopyGatewayImpl implements BookCopyGateway {

    private final BookCopyRepository bookCopyRepository;
    private final GetOneLibrary getOneLibrary;

    @Override
    public List<BookCopyJpa> findAllBooksOfLibrary(long libraryId) {
        LibraryJpa library = getOneLibrary.process(libraryId);
        return bookCopyRepository.findAllByLibrary(library);
    }

    @Override
    public List<BookCopyJpa> findBooksOfLibraryByName(String name, long id) {
        LibraryJpa library = getOneLibrary.process(id);
        return bookCopyRepository.findCopyByLibraryAndName(library, "%" + name + "%");
    }

    @Override
    public List<BookCopyJpa> findBooksOfLibraryByIsbn(String isbn, long id) {
        LibraryJpa library = getOneLibrary.process(id);
        return bookCopyRepository.findCopyByLibraryAndIsbn(library, isbn);
    }
}
