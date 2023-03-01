package br.com.projlib.bookshelf.infra.gateway.publisherjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<PublisherJpa, Long> {
}
