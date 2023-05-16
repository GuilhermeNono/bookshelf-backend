package br.com.projlib.bookshelf.infra.specification;

import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.query.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookSpecificationBuilder {
    private final List<SearchCriteria> params;

    public BookSpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final BookSpecificationBuilder with(String key,
                                              String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final BookSpecificationBuilder with(SearchCriteria
                                                      searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<BookJpa> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<BookJpa> result =
                new BookSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result =  SearchOperation.getDataOption(criteria
                    .getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new
                    BookSpecification(criteria))
                    : Specification.where(result).or(
                    new BookSpecification(criteria));
        }
        return result;
    }
}
