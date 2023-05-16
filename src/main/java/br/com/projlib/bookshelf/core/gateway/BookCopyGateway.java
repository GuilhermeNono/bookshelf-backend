package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BookCopyGateway {

    List<BookCopyJpa> findAllBooksOfLibrary(long LibraryId);

    Page<BookCopyJpa> findAll(Specification<BookCopyJpa> spec, Pageable pageable);
    List<BookCopyJpa> findAll();

    List<BookCopyJpa> findBooksOfLibraryByName(String name, long id);

    List<BookCopyJpa> findBooksOfLibraryByIsbn(String isbn, long id);
}
