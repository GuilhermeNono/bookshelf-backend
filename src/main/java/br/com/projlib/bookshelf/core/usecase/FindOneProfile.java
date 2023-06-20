package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.UserLibraryProfileGateway;
import br.com.projlib.bookshelf.infra.gateway.userlibraryprofileJpa.UserLibraryProfileJpa;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindOneProfile {
    private final UserLibraryProfileGateway userLibraryProfileGateway;

    public UserLibraryProfileJpa process(long id) {
        return userLibraryProfileGateway.findById(id).orElseThrow();
    }
}
