package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.domain.Library;
import br.com.projlib.bookshelf.core.gateway.LibraryGateway;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllLibraries {

    private final LibraryGateway libraryGateway;

    public List<Library> process() {
//        return libraryGateway.getAll();
        //TODO:Finalizar essa classe retornando uma list com a VO
        return null;
    }
}
