package br.com.projlib.bookshelf.infra.specification;

import br.com.projlib.bookshelf.infra.gateway.coursejpa.CourseJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.query.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CourseSpecificationBuilder {
    private final List<SearchCriteria> params;

    public CourseSpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final CourseSpecificationBuilder with(String key,
                                                 String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final CourseSpecificationBuilder with(SearchCriteria
                                                       searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<CourseJpa> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<CourseJpa> result =
                new CourseSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result =  SearchOperation.getDataOption(criteria
                    .getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new
                    CourseSpecification(criteria))
                    : Specification.where(result).or(
                    new CourseSpecification(criteria));
        }
        return result;
    }
}