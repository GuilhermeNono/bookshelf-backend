package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.LibraryGateway;
import br.com.projlib.bookshelf.infra.command.LibraryUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllLibrariesOfUser {
    private final LibraryGateway libraryGateway;

    public List<LibraryUserInfo> process() {
        return libraryGateway.getAllLibrariesOfUser();
    }
}
