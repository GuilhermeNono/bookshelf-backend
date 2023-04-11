package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.domain.ContactType;
import br.com.projlib.bookshelf.core.gateway.UserContactTypeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindContactTypeById {
    private final UserContactTypeGateway userContactTypeGateway;

    public ContactType process(long id){
        return userContactTypeGateway.findContactTypeById(id).orElseThrow().toDomain();
    }
}
