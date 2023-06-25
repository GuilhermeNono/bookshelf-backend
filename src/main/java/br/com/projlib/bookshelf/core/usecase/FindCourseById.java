package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.CourseGateway;
import br.com.projlib.bookshelf.infra.gateway.coursejpa.CourseJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindCourseById {
    private final CourseGateway courseGateway;

    public CourseJpa process(long id) {
        return courseGateway.findById(id).orElseThrow();
    }
}
