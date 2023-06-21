package br.com.projlib.bookshelf.infra.specification;

import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.query.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserLibrarySpecificationBuilder {
    private final List<SearchCriteria> params;

    public UserLibrarySpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final UserLibrarySpecificationBuilder with(String key,
                                                      String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final UserLibrarySpecificationBuilder with(SearchCriteria
                                                       searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<UserLibraryJpa> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<UserLibraryJpa> result =
                new UserLibrarySpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result =  SearchOperation.getDataOption(criteria
                    .getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new
                    UserLibrarySpecification(criteria))
                    : Specification.where(result).or(
                    new UserLibrarySpecification(criteria));
        }
        return result;
    }
}
