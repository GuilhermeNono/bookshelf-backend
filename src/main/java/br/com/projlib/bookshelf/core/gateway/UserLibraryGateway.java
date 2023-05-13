package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;

import java.util.List;

public interface UserLibraryGateway {
    List<LibraryJpa> getAllLibrariesOfUser(String userToken);
}