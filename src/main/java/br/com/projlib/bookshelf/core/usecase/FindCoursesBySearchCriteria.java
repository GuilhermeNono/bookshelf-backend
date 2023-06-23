package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.CourseGateway;
import br.com.projlib.bookshelf.entrypoint.http.response.CourseResponse;
import br.com.projlib.bookshelf.infra.gateway.coursejpa.CourseJpa;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindCoursesBySearchCriteria {
    private final CourseGateway courseGateway;
    private final ModelMapper modelMapper;
    public Page<CourseResponse> process(Specification<CourseJpa> spec, Pageable page) {
        Page<CourseJpa> all = courseGateway.findAll(spec, page);
        return all.map(c -> modelMapper.map(c, CourseResponse.class));
    }
}
