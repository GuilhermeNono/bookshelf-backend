package br.com.projlib.bookshelf.infra.gateway.bookcopyjpa;

import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookCopyRepository extends JpaRepository<BookCopyJpa, Long>, JpaSpecificationExecutor<BookCopyJpa> {
    List<BookCopyJpa> findAllByLibrary(LibraryJpa libraryJpa);

    @Query("select bc from BookCopyJpa bc where bc.library = ?1 and bc.book.name like ?2")
    List<BookCopyJpa> findCopyByLibraryAndName(LibraryJpa libraryJpa, String name);

    @Query("select bc from BookCopyJpa bc where bc.library = ?1 and bc.book.isbn like ?2")
    List<BookCopyJpa> findCopyByLibraryAndIsbn(LibraryJpa libraryJpa, String isbn);

    @Query("select bc from BookCopyJpa bc where bc.code = ?1")
    Optional<BookCopyJpa> findByCode(String code);

    @Query("select bc from BookCopyJpa bc where Month(bc.createdAt) = ?1 and bc.library = ?2 order by bc.createdAt desc")
    List<BookCopyJpa> findAllByMonthAndLibrary(int month, LibraryJpa libraryJpa);
}
