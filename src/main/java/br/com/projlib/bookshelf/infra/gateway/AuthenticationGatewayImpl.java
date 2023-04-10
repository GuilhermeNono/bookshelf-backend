package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.AuthenticationGateway;
import br.com.projlib.bookshelf.core.usecase.GenerateToken;
import br.com.projlib.bookshelf.core.usecase.LoadUserByUsername;
import br.com.projlib.bookshelf.core.usecase.ValidateToken;
import br.com.projlib.bookshelf.infra.command.AuthenticationToken;
import br.com.projlib.bookshelf.infra.exception.TokenDoesNotMatchException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationGatewayImpl implements AuthenticationGateway {

    private final AuthenticationManager authenticationManager;
    private final LoadUserByUsername loadUserByUsername;
    private final GenerateToken generateToken;
    private final ValidateToken validateToken;

    @Override
    public void authenticate(String email, String password) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @Override
    public AuthenticationToken buildToken(String email) {
        UserDetails userDetails = this.loadUserByUsername.process(email);

        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setToken(this.generateToken.process(userDetails));

        return authenticationToken;
    }

    @Override
    public void validateToken(AuthenticationToken token) {
        try {
            Optional<UserDetails> userDetails = getLoggedUser();
            if (userDetails.isPresent() && !this.validateToken.process(token.getToken(), userDetails.get())) {
                throw new TokenDoesNotMatchException();
            }
        } catch (SignatureException ex) {
            throw new TokenDoesNotMatchException();
        }
    }

    public static Optional<UserDetails> getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.ofNullable((UserDetails) authentication.getPrincipal());
        }

        return Optional.empty();
    }
}
