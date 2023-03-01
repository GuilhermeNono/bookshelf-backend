package br.com.projlib.bookshelf.infra.gateway.libpermissionjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibPermissionRepository extends JpaRepository<LibPermissionJpa, Long> {
}
