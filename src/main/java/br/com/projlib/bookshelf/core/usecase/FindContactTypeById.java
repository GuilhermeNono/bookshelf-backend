package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.gateway.UserContactTypeGateway;
import br.com.projlib.bookshelf.infra.gateway.usercontacttype.UserContactTypeJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindContactTypeById {
    private final UserContactTypeGateway userContactTypeGateway;

    public UserContactTypeJpa process(long id){
        return userContactTypeGateway.findContactTypeById(id).orElseThrow();
    }
}
