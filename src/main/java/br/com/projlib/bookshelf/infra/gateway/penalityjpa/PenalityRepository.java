package br.com.projlib.bookshelf.infra.gateway.penalityjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PenalityRepository extends JpaRepository<PenalityJpa, Long> {
}
