package br.com.projlib.bookshelf.infra.gateway.coursejpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepository extends JpaRepository<CourseJpa, Long>, JpaSpecificationExecutor<CourseJpa> {
}
