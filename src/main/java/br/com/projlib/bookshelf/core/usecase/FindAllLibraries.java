package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.LibraryGateway;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllLibraries {

    private final ModelMapper modelMapper;
    private final LibraryGateway libraryGateway;

    public List<LibraryJpa> process() {
        return libraryGateway.getAll();
    }
}
