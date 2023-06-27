package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.coursejpa.CourseJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface CourseGateway {
    List<CourseJpa> findAll();

    Page<CourseJpa> findAll(Specification<CourseJpa> spec, Pageable pageable);

    Optional<CourseJpa> findById(long id);
}