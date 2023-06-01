package br.com.projlib.bookshelf.infra.gateway.userlibraryprofileJpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLibraryProfileRepository extends JpaRepository<UserLibraryProfileJpa, Long> {
}
