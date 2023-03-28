package br.com.projlib.bookshelf.infra.gateway.syspermissionjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface SysPermissionRepository extends JpaRepository<SysPermissionJpa, Long> {
    @Query("select p from SysPermissionJpa p join p.profiles pro where pro.id = ?1")
    Collection<SysPermissionJpa> findByProfileId(long profileId);
}
