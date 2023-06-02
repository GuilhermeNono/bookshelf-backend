package br.com.projlib.bookshelf.infra.gateway.borrowingjpa;

import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<BorrowingJpa, Long>, JpaSpecificationExecutor<BorrowingJpa> {

    @Query("select bw" +
            " from BorrowingJpa bw" +
            " where Month(bw.loanDate) = ?1 " +
            "or Month(bw.returnDate) = ?1 " +
            "and bw.bookCopy.library = ?2" +
            " order by bw.loanDate desc ")
    List<BorrowingJpa> findAllByMonthAndLibrary(int month, LibraryJpa library);
}
