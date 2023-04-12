package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.UserContactGateway;
import br.com.projlib.bookshelf.infra.gateway.usercontact.UserContactJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateContact {
    private final UserContactGateway userContactGateway;

    public UserContactJpa process(UserContactJpa userContact) {
       return userContactGateway.create(userContact);
    }
}
