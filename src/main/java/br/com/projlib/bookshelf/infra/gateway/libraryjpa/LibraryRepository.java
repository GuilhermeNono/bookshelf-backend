package br.com.projlib.bookshelf.infra.gateway.libraryjpa;

import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryRepository extends JpaRepository<LibraryJpa, Long> {
    @Query("select lib from LibraryJpa lib join lib.libraryUsers libUser where libUser = ?1")
    List<LibraryJpa> findAllLibraryUser(UserLibraryJpa userLibrary);
}
