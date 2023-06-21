package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.UserLibraryGateway;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLibraryGatewayImpl implements UserLibraryGateway {

    private final UserLibraryRepository userLibraryRepository;

    @Override
    public List<UserLibraryJpa> findAll() {
        return userLibraryRepository.findAll();
    }

    @Override
    public Page<UserLibraryJpa> findAll(Specification<UserLibraryJpa> spec, Pageable page) {
        return userLibraryRepository.findAll(spec, page);
    }

    @Override
    public Optional<UserLibraryJpa> findById(long id) {
        return userLibraryRepository.findById(id);
    }
}
