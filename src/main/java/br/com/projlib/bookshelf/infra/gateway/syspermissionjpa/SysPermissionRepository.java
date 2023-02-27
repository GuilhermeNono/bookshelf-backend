package br.com.projlib.bookshelf.infra.gateway.syspermissionjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SysPermissionRepository extends JpaRepository<SysPermissionJpa, Long> {
}
