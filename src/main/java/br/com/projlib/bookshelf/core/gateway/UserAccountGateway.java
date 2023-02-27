package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;

public interface UserAccountGateway {

    UserAccountJpa getUser();

}
