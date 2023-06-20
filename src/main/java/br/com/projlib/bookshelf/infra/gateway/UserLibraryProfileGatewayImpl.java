package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.UserLibraryProfileGateway;
import br.com.projlib.bookshelf.infra.gateway.userlibraryprofileJpa.UserLibraryProfileJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryprofileJpa.UserLibraryProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserLibraryProfileGatewayImpl implements UserLibraryProfileGateway {

    private final UserLibraryProfileRepository userLibraryProfileRepository;

    @Override
    public List<UserLibraryProfileJpa> findAll() {
        return userLibraryProfileRepository.findAll();
    }

    @Override
    public Optional<UserLibraryProfileJpa> findById(long id) {
        return userLibraryProfileRepository.findById(id);
    }
}
