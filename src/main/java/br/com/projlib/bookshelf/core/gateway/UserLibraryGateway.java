package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;

import java.util.List;
import java.util.Optional;

public interface UserLibraryGateway {
    List<LibraryJpa> getAllLibrariesOfUser(String userToken);

    Optional<UserLibraryJpa> findById(long id);
}
