package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.PublisherGateway;
import br.com.projlib.bookshelf.infra.gateway.publisherjpa.PublisherJpa;
import br.com.projlib.bookshelf.infra.gateway.publisherjpa.PublisherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherGatewayImpl implements PublisherGateway {

    private final PublisherRepository publisherRepository;

    @Override
    public Optional<PublisherJpa> findById(long id) {
        return publisherRepository.findById(id);
    }

    @Override
    @Transactional
    public PublisherJpa save(PublisherJpa publisherJpa) {
        return publisherRepository.save(publisherJpa);
    }

    @Override
    public PublisherJpa findByName(String name) {
        return publisherRepository.findPublisherJpaByName(name);
    }
}
