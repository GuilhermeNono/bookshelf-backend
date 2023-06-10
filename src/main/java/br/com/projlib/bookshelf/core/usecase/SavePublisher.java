package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.PublisherGateway;
import br.com.projlib.bookshelf.infra.gateway.publisherjpa.PublisherJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SavePublisher {
    private final PublisherGateway publisherGateway;

    public PublisherJpa process(PublisherJpa publisherJpa) {
        return publisherGateway.save(publisherJpa);
    }
}
