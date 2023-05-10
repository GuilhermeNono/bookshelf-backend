package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.BookGateway;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookGatewayImpl implements BookGateway {

    private final BookRepository bookRepository;

    @Override
    public List<BookJpa> findAll() {
        return bookRepository.findAll();
    }
}
