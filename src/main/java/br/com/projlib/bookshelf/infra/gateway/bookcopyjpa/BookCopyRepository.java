package br.com.projlib.bookshelf.infra.gateway.bookcopyjpa;

import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopyJpa, Long>, JpaSpecificationExecutor<BookCopyJpa> {
    List<BookCopyJpa> findAllByLibrary(LibraryJpa libraryJpa);

    @Query("select bc from BookCopyJpa bc where bc.library = ?1 and bc.book.name like ?2")
    List<BookCopyJpa> findCopyByLibraryAndName(LibraryJpa libraryJpa, String name);

    @Query("select bc from BookCopyJpa bc where bc.library = ?1 and bc.book.isbn like ?2")
    List<BookCopyJpa> findCopyByLibraryAndIsbn(LibraryJpa libraryJpa, String isbn);
}
