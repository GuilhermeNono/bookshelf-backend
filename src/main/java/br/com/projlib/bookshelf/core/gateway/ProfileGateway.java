package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.userprofile.UserProfileJpa;

import java.util.Optional;

public interface ProfileGateway {

    Optional<UserProfileJpa> findProfileById(long id);

}
