package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.command.LibraryUserInfo;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;

import java.util.List;
import java.util.Optional;

public interface LibraryGateway {
    List<LibraryJpa> getAll();

    Optional<LibraryJpa> getOne(long id);

    List<LibraryUserInfo> getAllLibrariesOfUser();
}
