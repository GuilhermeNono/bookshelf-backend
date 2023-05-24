package br.com.projlib.bookshelf.infra.specification;

import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.query.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BorrowingSpecificationBuilder {
    private final List<SearchCriteria> params;

    public BorrowingSpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final BorrowingSpecificationBuilder with(String key,
                                                   String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final BorrowingSpecificationBuilder with(SearchCriteria
                                                           searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<BorrowingJpa> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<BorrowingJpa> result =
                new BorrowingSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result =  SearchOperation.getDataOption(criteria
                    .getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new
                    BorrowingSpecification(criteria))
                    : Specification.where(result).or(
                    new BorrowingSpecification(criteria));
        }
        return result;
    }
}
