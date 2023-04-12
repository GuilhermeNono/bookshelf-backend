package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.core.domain.Contact;
import br.com.projlib.bookshelf.infra.gateway.usercontact.UserContactJpa;

public interface UserContactGateway {
    UserContactJpa create(UserContactJpa userContactJpa);
}
