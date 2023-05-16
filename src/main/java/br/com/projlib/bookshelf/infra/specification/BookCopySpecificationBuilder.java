package br.com.projlib.bookshelf.infra.specification;

import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.query.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookCopySpecificationBuilder {
    private final List<SearchCriteria> params;

    public BookCopySpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final BookCopySpecificationBuilder with(String key,
                                               String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final BookCopySpecificationBuilder with(SearchCriteria
                                                       searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<BookCopyJpa> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<BookCopyJpa> result =
                new BookCopySpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result =  SearchOperation.getDataOption(criteria
                    .getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new
                    BookCopySpecification(criteria))
                    : Specification.where(result).or(
                    new BookCopySpecification(criteria));
        }
        return result;
    }
}
