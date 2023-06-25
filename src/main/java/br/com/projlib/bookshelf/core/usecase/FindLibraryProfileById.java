package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.UserLibraryProfileGateway;
import br.com.projlib.bookshelf.infra.gateway.userlibraryprofileJpa.UserLibraryProfileJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindLibraryProfileById {
    private final UserLibraryProfileGateway userLibraryProfileGateway;

    public UserLibraryProfileJpa process(long id) {
        return userLibraryProfileGateway.findById(id).orElseThrow();
    }
}
