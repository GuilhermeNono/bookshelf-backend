package br.com.projlib.bookshelf.core.gateway;

import br.com.projlib.bookshelf.infra.command.AuthenticationToken;

public interface AuthenticationGateway {
    void authenticate(String username, String password);
    AuthenticationToken buildToken(String username);
    void validateToken(AuthenticationToken token);
}
