package br.com.projlib.bookshelf.infra.specification;

import br.com.projlib.bookshelf.infra.gateway.coursejpa.CourseJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import br.com.projlib.bookshelf.infra.query.SearchCriteria;
import br.com.projlib.bookshelf.infra.query.SearchOperation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class CourseSpecification implements
        Specification<CourseJpa> {
    private final SearchCriteria searchCriteria;

    public CourseSpecification(final SearchCriteria
                                     searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<CourseJpa> root,
                                 CriteriaQuery<?> query, CriteriaBuilder cb) {
        String strToSearch = searchCriteria.getValue()
                .toString().toLowerCase();

        switch(Objects.requireNonNull(
                SearchOperation.getSimpleOperation
                        (searchCriteria.getOperation()))){
            case CONTAINS:
                return cb.like(cb.lower(root
                                .get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                return cb.notLike(cb.lower(root
                                .get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%");
            case EQUAL:
                if(searchCriteria.getFilterKey().equals("library")) {
                  return cb.equal(libraryJoin(root).get("id"), strToSearch);
                }
                return cb.equal(root
                                .get(searchCriteria.getFilterKey()),
                        strToSearch);
            default:
                return null;
        }
    }

    private Join<CourseJpa, LibraryJpa> libraryJoin(Root<CourseJpa>
                                                                 root){
        return root.join("library");
    }
//
//    private Join<BookCopyJpa, LibraryJpa> libraryJoin(Root<BookCopyJpa>
//                                                        root){
//        return root.join("library");
//    }
}