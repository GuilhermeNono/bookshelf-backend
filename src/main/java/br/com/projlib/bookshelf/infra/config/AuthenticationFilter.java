package br.com.projlib.bookshelf.infra.config;

import br.com.projlib.bookshelf.core.usecase.FindAuthoritiesByUser;
import br.com.projlib.bookshelf.core.usecase.FindUsernameFromToken;
import br.com.projlib.bookshelf.core.usecase.LoadUserByUsername;
import br.com.projlib.bookshelf.core.usecase.ValidateToken;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    public static final String TOKEN_BEARER = "Bearer ";
    private static final String REQUEST_HEADER_AUTHORIZATION = "Authorization";

    private final LoadUserByUsername loadUserByUsername;
    private final FindAuthoritiesByUser findAuthoritiesByUser;
    private final FindUsernameFromToken findUsernameFromToken;
    private final ValidateToken validateToken;

    @Autowired
    public AuthenticationFilter(
                                LoadUserByUsername loadUserByUsername,
                                FindAuthoritiesByUser findAuthoritiesByUser,
                                FindUsernameFromToken findUsernameFromToken,
                                ValidateToken validateToken) {
        super();
        this.validateToken = validateToken;
        this.findUsernameFromToken = findUsernameFromToken;
        this.loadUserByUsername = loadUserByUsername;
        this.findAuthoritiesByUser = findAuthoritiesByUser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenAuthentication = httpServletRequest.getHeader(REQUEST_HEADER_AUTHORIZATION);

        String token = null;
        String username = null;
        if (Objects.nonNull(requestTokenAuthentication) && requestTokenAuthentication.startsWith(TOKEN_BEARER)) {
            token = requestTokenAuthentication.substring(TOKEN_BEARER.length());
            try {
                username = this.findUsernameFromToken.process(token);
            } catch (IllegalArgumentException e) {
                log.warn("Unable to get JWT Token");
            } catch (ExpiredJwtException e){
                log.warn("JWT Token has expired");
            } catch (RuntimeException e) {
                log.warn(e.getMessage());
            }
        }

        if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetails userDetails = this.loadUserByUsername.process(username);

            if (this.validateToken.process(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, this.findAuthoritiesByUser.process(userDetails));

                Map<String, Object> details = new HashMap<>();
//                details.put(TokenService.ESTABLISHMENT_PROPERTY, this.tokenService.getPinnedEstablishmentFromToken(token).orElse(null));

                usernamePasswordAuthenticationToken.setDetails(details);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
