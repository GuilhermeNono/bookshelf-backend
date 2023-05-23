package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.UserLibraryGateway;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserLibraryById {
    private final UserLibraryGateway libraryGateway;

    public UserLibraryJpa process(long id) {
        return libraryGateway.findById(id).orElseThrow();
    }
}
