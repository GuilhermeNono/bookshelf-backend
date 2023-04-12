package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.UserContactTypeGateway;
import br.com.projlib.bookshelf.infra.gateway.usercontacttype.UserContactTypeJpa;
import br.com.projlib.bookshelf.infra.gateway.usercontacttype.UserContactTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserContactTypeGatewayImpl implements UserContactTypeGateway {

    private final UserContactTypeRepository userContactTypeRepository;

    @Override
    public Optional<UserContactTypeJpa> findContactTypeById(long id) {
        return userContactTypeRepository.findById(id);
    }
}
