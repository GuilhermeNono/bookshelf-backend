package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.AuthorGateway;
import br.com.projlib.bookshelf.infra.gateway.authorjpa.AuthorJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAuthorByFullName {
    private final AuthorGateway authorGateway;

    public AuthorJpa process(String firstName, String lastName) {
        return authorGateway.findByFullName(firstName, lastName);
    }
}
