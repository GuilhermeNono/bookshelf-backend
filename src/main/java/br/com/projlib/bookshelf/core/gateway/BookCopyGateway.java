package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface BookCopyGateway {

    List<BookCopyJpa> findAllBooksOfLibrary(long LibraryId);

    Page<BookCopyJpa> findAll(Specification<BookCopyJpa> spec, Pageable pageable);
    List<BookCopyJpa> findAll();
    void create(BookCopyJpa bookCopy);
    Optional<BookCopyJpa> findById(long id);
    Optional<BookCopyJpa> findByCode(String code);
    List<BookCopyJpa> findBooksOfLibraryByName(String name, long id);

    List<BookCopyJpa> findBooksOfLibraryByIsbn(String isbn, long id);
}
