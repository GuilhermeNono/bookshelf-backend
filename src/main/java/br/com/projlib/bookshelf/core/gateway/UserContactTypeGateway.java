package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.usercontacttype.UserContactTypeJpa;

import java.util.Optional;

public interface UserContactTypeGateway {
    Optional<UserContactTypeJpa> findContactTypeById(long id);

}
