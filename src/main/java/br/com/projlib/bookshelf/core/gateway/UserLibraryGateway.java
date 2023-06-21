package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface UserLibraryGateway {
    List<UserLibraryJpa> findAll();

    Page<UserLibraryJpa> findAll(Specification<UserLibraryJpa> spec, Pageable page);

    Optional<UserLibraryJpa> findById(long id);
}
