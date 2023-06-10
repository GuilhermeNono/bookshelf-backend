package br.com.projlib.bookshelf.infra.gateway.authorjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<AuthorJpa, Long> {

    @Query("select a from AuthorJpa a where a.firstName = ?1 and a.lastName = ?2")
    AuthorJpa findByFullName(String firstName, String lastName);
}
