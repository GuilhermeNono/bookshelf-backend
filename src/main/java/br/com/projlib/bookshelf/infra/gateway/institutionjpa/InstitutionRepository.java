package br.com.projlib.bookshelf.infra.gateway.institutionjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<InstitutionJpa, Long> {
}
