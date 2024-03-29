package br.com.projlib.bookshelf.infra.specification;

import br.com.projlib.bookshelf.infra.gateway.bookcopyjpa.BookCopyJpa;
import br.com.projlib.bookshelf.infra.gateway.bookjpa.BookJpa;
import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
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

public class BorrowingSpecification implements
        Specification<BorrowingJpa> {
    private final SearchCriteria searchCriteria;

    public BorrowingSpecification(final SearchCriteria
                                         searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<BorrowingJpa> root,
                                 CriteriaQuery<?> query, CriteriaBuilder cb) {
        String strToSearch = searchCriteria.getValue()
                .toString().toLowerCase();

        switch(Objects.requireNonNull(
                SearchOperation.getSimpleOperation
                        (searchCriteria.getOperation()))){
            case CONTAINS:
                if(searchCriteria.getFilterKey().equals("name")) {
                  return cb.like(userAccountJoin(root).get("personName"), strToSearch);
                }
                return cb.like(cb.lower(root
                                .get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                return cb.notLike(cb.lower(root
                                .get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%");
            case EQUAL:
                if(searchCriteria.getFilterKey().equals("code"))
                {
                    return cb.equal(cb.lower(bookCopyJoin(root).
                                    <String>get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getFilterKey().equals("bookName")) {
                    return cb.equal(bookJoin(root).get("name"), strToSearch);
                } else if(searchCriteria.getFilterKey().equals("userId")){
                    return cb.equal(userLibraryJoin(root).get("id"), strToSearch);
                } else if(searchCriteria.getFilterKey().equals("overdue")){
                    return cb.equal(root.get(searchCriteria.getFilterKey()), Boolean.parseBoolean(strToSearch));
                }else if(searchCriteria.getFilterKey().equals("library")){
                    return cb.equal(libraryJoin(root).get("id"), strToSearch);
                }else if(searchCriteria.getFilterKey().equals("loanDateMonth")){
                    return cb.equal(cb.function("MONTH", Integer.class, root.get("loanDate")), strToSearch);
                }else if(searchCriteria.getFilterKey().equals("returnDateMonth")){
                    return cb.equal(cb.function("MONTH", Integer.class, root.get("returnDate")), strToSearch);
                }else if(searchCriteria.getFilterKey().equals("loanDateYear")){
                    return cb.equal(cb.function("YEAR", Integer.class, root.get("loanDate")), strToSearch);
                }else if(searchCriteria.getFilterKey().equals("returnDateYear")) {
                    return cb.equal(cb.function("YEAR", Integer.class, root.get("returnDate")), strToSearch);
                }else if(searchCriteria.getFilterKey().equals("active")){
                    return cb.equal(root.get(searchCriteria.getFilterKey()), Boolean.parseBoolean(strToSearch));
                }
                    return cb.equal(root
                                .get(searchCriteria.getFilterKey()),
                        strToSearch);
            default:
                return null;
        }
    }
    private Join<BorrowingJpa, BookCopyJpa> bookCopyJoin(Root<BorrowingJpa>
                                                        root){
        return root.join("bookCopy");
    }

    private Join<BorrowingJpa, LibraryJpa> libraryJoin(Root<BorrowingJpa>
                                                                 root){
        return root.join("bookCopy").join("library");
    }

    private Join<BorrowingJpa, BookJpa> bookJoin(Root<BorrowingJpa>
                                                              root){
        return root.join("bookCopy").join("book");
    }

    private Join<BorrowingJpa, UserLibraryJpa> userLibraryJoin(Root<BorrowingJpa> root) {
        return root.join("userLibrary");
    }

    private Join<BorrowingJpa, UserAccountJpa> userAccountJoin(Root<BorrowingJpa> root) {
        return userLibraryJoin(root).join("userAccount");
    }
}
