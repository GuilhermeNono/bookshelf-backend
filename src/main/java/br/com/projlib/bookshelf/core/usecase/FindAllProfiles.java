package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.UserLibraryProfileGateway;
import br.com.projlib.bookshelf.infra.gateway.userlibraryprofileJpa.UserLibraryProfileJpa;
import br.com.projlib.bookshelf.infra.gateway.userprofile.UserProfileJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllProfiles {
    private final UserLibraryProfileGateway userLibraryProfileGateway;

    public List<UserLibraryProfileJpa> process() {
        return userLibraryProfileGateway.findAll();
    }
}
