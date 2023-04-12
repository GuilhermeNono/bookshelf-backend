package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.domain.Contact;
import br.com.projlib.bookshelf.core.gateway.UserContactGateway;
import br.com.projlib.bookshelf.infra.gateway.usercontact.UserContactJpa;
import br.com.projlib.bookshelf.infra.gateway.usercontact.UserContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserContactGatewayImpl implements UserContactGateway {
    private final UserContactRepository userContactRepository;
    @Override
    @Transactional
    public UserContactJpa create(UserContactJpa userContactJpa) {
        return userContactRepository.save(userContactJpa);
    }
}
