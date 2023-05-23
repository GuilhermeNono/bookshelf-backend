package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.UserLibraryGateway;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLibraryGatewayImpl implements UserLibraryGateway {

    private final UserLibraryRepository libraryRepository;

    @Override
    public List<LibraryJpa> getAllLibrariesOfUser(String userToken) {
        return null;
    }

    @Override
    public Optional<UserLibraryJpa> findById(long id) {
        return libraryRepository.findById(id);
    }
}
