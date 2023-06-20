package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.userlibraryprofileJpa.UserLibraryProfileJpa;

import java.util.List;
import java.util.Optional;

public interface UserLibraryProfileGateway {

    List<UserLibraryProfileJpa> findAll();

    Optional<UserLibraryProfileJpa> findById(long id);
}
