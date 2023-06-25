package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.CourseGateway;
import br.com.projlib.bookshelf.infra.gateway.coursejpa.CourseJpa;
import br.com.projlib.bookshelf.infra.gateway.coursejpa.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CourseGatewayImpl implements CourseGateway {

    private final CourseRepository courseRepository;

    @Override
    public List<CourseJpa> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Page<CourseJpa> findAll(Specification<CourseJpa> spec, Pageable pageable) {
        return courseRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<CourseJpa> findById(long id) {
        return courseRepository.findById(id);
    }
}