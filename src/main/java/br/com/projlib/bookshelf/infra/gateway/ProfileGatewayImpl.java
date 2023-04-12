package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.ProfileGateway;
import br.com.projlib.bookshelf.infra.gateway.userprofile.UserProfileJpa;
import br.com.projlib.bookshelf.infra.gateway.userprofile.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileGatewayImpl implements ProfileGateway {

    private final UserProfileRepository userProfileRepository;

    @Override
    public Optional<UserProfileJpa> findProfileById(long id) {
        return userProfileRepository.findById(id);
    }
}
