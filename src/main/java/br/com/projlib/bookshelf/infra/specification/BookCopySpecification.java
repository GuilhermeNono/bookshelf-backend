package br.com.projlib.bookshelf.infra.specification;

import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.query.SearchOperation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class BookCopySpecification implements
        Specification<BookCopyJpa> {
    private final SearchCriteria searchCriteria;

    public BookCopySpecification(final SearchCriteria
                                     searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<BookCopyJpa> root,
                                 CriteriaQuery<?> query, CriteriaBuilder cb) {
        String strToSearch = searchCriteria.getValue()
                .toString().toLowerCase();

        switch(Objects.requireNonNull(
                SearchOperation.getSimpleOperation
                        (searchCriteria.getOperation()))){
            case CONTAINS:
                if(searchCriteria.getFilterKey().equals("name"))
                {
                    return cb.like(cb.lower(bookJoin(root).
                                    <String>get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                }
                return cb.like(cb.lower(root
                                .get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                if(searchCriteria.getFilterKey().equals("firstName"))
                {
                    return cb.notLike(cb.lower(bookJoin(root).
                                    <String>get(searchCriteria.getFilterKey())),
                            "%" + strToSearch +"%");
                }
                return cb.notLike(cb.lower(root
                                .get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%");
            case EQUAL:
                if(searchCriteria.getFilterKey().equals("name"))
                {
                    return cb.equal(cb.lower(bookJoin(root).
                                    <String>get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getFilterKey().equals("id")) {
                    return cb.equal(libraryJoin(root).get(searchCriteria.getFilterKey()), strToSearch);
                } else if (searchCriteria.getFilterKey().equals("code")) {
                    return cb.equal(root.get(searchCriteria.getFilterKey()), strToSearch);
                }
                return cb.equal(bookJoin(root)
                                .get(searchCriteria.getFilterKey()),
                        strToSearch);
            default:
                return null;
        }
    }
    private Join<BookCopyJpa, BookJpa> bookJoin(Root<BookCopyJpa>
                                                        root){
        return root.join("book");
    }

    private Join<BookCopyJpa, LibraryJpa> libraryJoin(Root<BookCopyJpa>
                                                        root){
        return root.join("library");
    }
}
