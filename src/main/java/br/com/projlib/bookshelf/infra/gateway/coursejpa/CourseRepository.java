package br.com.projlib.bookshelf.infra.gateway.coursejpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseJpa, Long> {
}
