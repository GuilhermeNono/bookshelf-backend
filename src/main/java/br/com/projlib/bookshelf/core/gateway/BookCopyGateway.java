package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;

import java.util.List;

public interface BookCopyGateway {

    List<BookCopyJpa> findAllBooksOfLibrary(long LibraryId);

    List<BookCopyJpa> findBooksOfLibraryByName(String name, long id);

    List<BookCopyJpa> findBooksOfLibraryByIsbn(String isbn, long id);
}
