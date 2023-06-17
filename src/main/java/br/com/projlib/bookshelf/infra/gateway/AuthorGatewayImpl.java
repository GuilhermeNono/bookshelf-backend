package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.AuthorGateway;
import br.com.projlib.bookshelf.infra.gateway.authorjpa.AuthorJpa;
import br.com.projlib.bookshelf.infra.gateway.authorjpa.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorGatewayImpl implements AuthorGateway {
    private final AuthorRepository authorRepository;

    @Override
    public Optional<AuthorJpa> findById(long id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional
    public AuthorJpa save(AuthorJpa authorJpa) {
        return authorRepository.save(authorJpa);
    }

    @Override
    public AuthorJpa findByFullName(String firstName, String lastName) {
        return authorRepository.findByFullName(firstName, lastName);
    }

    @Override
    public List<AuthorJpa> findAll() {
        return authorRepository.findAll();
    }
}
