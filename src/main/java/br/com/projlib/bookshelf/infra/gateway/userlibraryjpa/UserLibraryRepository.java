package br.com.projlib.bookshelf.infra.gateway.userlibraryjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserLibraryRepository extends JpaRepository<UserLibraryJpa, Long>, JpaSpecificationExecutor<UserLibraryJpa> {
}
