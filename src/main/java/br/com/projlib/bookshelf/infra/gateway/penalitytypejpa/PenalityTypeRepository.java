package br.com.projlib.bookshelf.infra.gateway.penalitytypejpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PenalityTypeRepository extends JpaRepository<PenalityTypeJpa, Long> {
}
