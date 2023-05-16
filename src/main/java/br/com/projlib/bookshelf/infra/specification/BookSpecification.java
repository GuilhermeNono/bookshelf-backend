package br.com.projlib.bookshelf.infra.specification;

import br.com.projlib.bookshelf.infra.gateway.authorjpa.AuthorJpa;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.query.SearchOperation;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;


public class BookSpecification implements
        Specification<BookJpa> {
    private final SearchCriteria searchCriteria;

    public BookSpecification(final SearchCriteria
                                         searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<BookJpa> root,
                                 CriteriaQuery<?> query, CriteriaBuilder cb) {
        String strToSearch = searchCriteria.getValue()
                .toString().toLowerCase();

        switch(Objects.requireNonNull(
                SearchOperation.getSimpleOperation
                        (searchCriteria.getOperation()))){
            case CONTAINS:
                if(searchCriteria.getFilterKey().equals("firstName"))
                {
                    return cb.like(cb.lower(authorJoin(root).
                                    <String>get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                }
                return cb.like(cb.lower(root
                                .get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                if(searchCriteria.getFilterKey().equals("firstName"))
                {
                    return cb.notLike(cb.lower(authorJoin(root).
                                    <String>get(searchCriteria.getFilterKey())),
                            "%" + strToSearch +"%");
                }
                return cb.notLike(cb.lower(root
                                .get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%");
            case EQUAL:
                if(searchCriteria.getFilterKey().equals("firstName"))
                {
                    return cb.equal(cb.lower(authorJoin(root).
                                    <String>get(searchCriteria.getFilterKey())),
                            strToSearch);
                }
                return cb.equal(root
                                .get(searchCriteria.getFilterKey()),
                        strToSearch);
            default:
                return null;
        }
    }
    private Join<BookJpa, AuthorJpa> authorJoin(Root<BookJpa>
                                                             root){
        return root.join("authors");
    }
}
