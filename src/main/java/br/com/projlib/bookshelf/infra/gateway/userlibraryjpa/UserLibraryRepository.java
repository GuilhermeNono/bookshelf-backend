package br.com.projlib.bookshelf.infra.gateway.userlibraryjpa;

import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserLibraryRepository extends JpaRepository<UserLibraryJpa, Long> {
    @Query("select lib from LibraryJpa lib join lib.libraryUsers libUser where libUser = ?1")
    List<LibraryJpa> findAllLibraryUser(UserLibraryJpa userLibrary);
}
