package br.com.projlib.bookshelf.infra.gateway.userlibraryjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLibraryRepository extends JpaRepository<UserLibraryJpa, Long> {
}
