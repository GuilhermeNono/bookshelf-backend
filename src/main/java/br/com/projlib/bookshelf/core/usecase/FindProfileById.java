package br.com.projlib.bookshelf.core.usecase;

import br.com.projlib.bookshelf.core.domain.Profile;
import br.com.projlib.bookshelf.core.gateway.ProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindProfileById {

    private final ProfileGateway profileGateway;

    public Profile process(long id) {
        return profileGateway.findProfileById(id).orElseThrow().toDomain();
    }
}
