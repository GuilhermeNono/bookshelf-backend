package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.BookCopyGateway;
import br.com.projlib.bookshelf.core.usecase.FindOneLibrary;
import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyRepository;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCopyGatewayImpl implements BookCopyGateway {

    private final BookCopyRepository bookCopyRepository;
    private final FindOneLibrary findOneLibrary;

    @Override
    public List<BookCopyJpa> findAllBooksOfLibrary(long libraryId) {
        LibraryJpa library = findOneLibrary.process(libraryId);
        return bookCopyRepository.findAllByLibrary(library);
    }

    @Override
    public List<BookCopyJpa> findAll() {
        return bookCopyRepository.findAll();
    }

    @Override
    @Transactional
    public void create(BookCopyJpa bookCopy) {
        bookCopyRepository.save(bookCopy);
    }

    @Override
    public Optional<BookCopyJpa> findById(long id) {
        return bookCopyRepository.findById(id);
    }

    @Override
    public Optional<BookCopyJpa> findByCode(String code) {
        return bookCopyRepository.findByCode(code);
    }

    @Override
    public Page<BookCopyJpa> findAll(Specification<BookCopyJpa> spec, Pageable pageable) {
        return bookCopyRepository.findAll(spec, pageable);
    }



    @Override
    public List<BookCopyJpa> findBooksOfLibraryByName(String name, long id) {
        LibraryJpa library = findOneLibrary.process(id);
        return bookCopyRepository.findCopyByLibraryAndName(library, "%" + name + "%");
    }

    @Override
    public List<BookCopyJpa> findBooksOfLibraryByIsbn(String isbn, long id) {
        LibraryJpa library = findOneLibrary.process(id);
        return bookCopyRepository.findCopyByLibraryAndIsbn(library, isbn);
    }
}
