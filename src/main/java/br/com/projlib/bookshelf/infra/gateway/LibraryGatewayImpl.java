package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.LibraryGateway;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryGatewayImpl implements LibraryGateway {

    private final LibraryRepository libraryRepository;

    @Override
    public List<LibraryJpa> getAll() {
        return libraryRepository.findAll();
    }

    @Override
    public Optional<LibraryJpa> getOne(long id) {
        return libraryRepository.findById(id);
    }
}
