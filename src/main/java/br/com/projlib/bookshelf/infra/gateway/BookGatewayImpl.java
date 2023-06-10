package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.BookGateway;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookGatewayImpl implements BookGateway {

    private final BookRepository bookRepository;

    @Override
    public List<BookJpa> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page<BookJpa> findAll(Specification<BookJpa> spec, Pageable page) {
        return bookRepository.findAll(spec, page);
    }

    @Override
    public List<BookJpa> findByName(String name) {
        return bookRepository.findByName("%"+ name + "%");
    }

    @Override
    public List<BookJpa> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Optional<BookJpa> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public BookJpa save(BookJpa book) {
        return bookRepository.save(book);
    }
}
