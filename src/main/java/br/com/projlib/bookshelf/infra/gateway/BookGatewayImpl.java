package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.BookGateway;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookRepository;
import lombok.RequiredArgsConstructor;
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
}
