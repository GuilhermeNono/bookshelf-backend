package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.AuthorGateway;
import br.com.projlib.bookshelf.infra.gateway.authorjpa.AuthorJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllAuthor {
    private final AuthorGateway authorGateway;

    public List<AuthorJpa> process() {
        return authorGateway.findAll();
    }
}
