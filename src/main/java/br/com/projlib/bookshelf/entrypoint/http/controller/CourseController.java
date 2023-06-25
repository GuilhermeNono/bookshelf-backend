package br.com.projlib.bookshelf.entrypoint.http.controller;

import br.com.projlib.bookshelf.core.usecase.FindCoursesBySearchCriteria;
import br.com.projlib.bookshelf.entrypoint.http.request.SearchRequest;
import br.com.projlib.bookshelf.entrypoint.http.response.CourseResponse;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.specification.CourseSpecificationBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
@Tag(name = "Course")
@Slf4j
public class CourseController {

    private final FindCoursesBySearchCriteria findCoursesBySearchCriteria;

    @Operation(summary = "Search courses")
    @PostMapping("/search")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Page<CourseResponse>> searchCourses
            (@RequestParam(name = "pageNum",
                    defaultValue = "0") int pageNum,
             @RequestParam(name = "pageSize",
                     defaultValue = "10") int pageSize,
             @RequestBody SearchRequest
                     courseSearch){
        try {
            CourseSpecificationBuilder builder = new
                    CourseSpecificationBuilder();
            List<SearchCriteria> criteriaList =
                    courseSearch.getSearchCriteriaList();
            if(criteriaList != null){
                criteriaList.forEach(x->
                {x.setDataOption(courseSearch
                        .getDataOption());
                    builder.with(x);
                });
            }

            Pageable page = PageRequest.of(pageNum, pageSize,
                    Sort.by("active")
                            .descending());

            Page<CourseResponse> userLibPage =
                    findCoursesBySearchCriteria.process(builder.build(),
                            page);

            return new ResponseEntity<>(userLibPage, HttpStatus.OK);
        } catch (RuntimeException e) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            throw e;
        }
    }

}