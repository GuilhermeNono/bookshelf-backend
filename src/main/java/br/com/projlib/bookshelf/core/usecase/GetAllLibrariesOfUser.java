package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.LibraryGateway;
import br.com.projlib.bookshelf.infra.command.LibraryUserInfo;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllLibrariesOfUser {
    private final LibraryGateway libraryGateway;

    public List<List<LibraryUserInfo>> process() {
        return libraryGateway.getAllLibrariesOfUser();
    }
}
