package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.domain.Contact;
import br.com.projlib.bookshelf.core.gateway.UserContactGateway;
import br.com.projlib.bookshelf.core.gateway.UserContactTypeGateway;
import br.com.projlib.bookshelf.infra.gateway.usercontact.UserContactJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserContact {
    private final UserContactGateway userContactTypeGateway;

    public UserContactJpa process(UserContactJpa userContactJpa){
        return userContactTypeGateway.create(userContactJpa);
    }
}
