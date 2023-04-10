package br.com.projlib.bookshelf.infra.gateway;

import br.com.projlib.bookshelf.core.gateway.TokenGateway;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TokenGatewayImpl implements TokenGateway {

    private final UserAccountRepository userAccountRepository;

    private static final long JWT_TOKEN_VALIDITY = 16L * 60L * 60L;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    @Transactional
    public String getUsernameFromToken(String token) {
        String cpf = this.getClaimFromToken(token, Claims::getSubject);
        return userAccountRepository.findByCpf(cpf).orElseThrow().getEmail();
    }

    @Override
    public Date getExpirationDateFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getExpiration);
    }

    @Override
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }

    @Override
    public Boolean isTokenExpired(String token) {
        final Date expirationDateFromToken = this.getExpirationDateFromToken(token);

        return expirationDateFromToken.before(new Date());
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return this.doGenerateToken(claims, userDetails.getUsername());
    }

    @Override
    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean validateToken(String token, UserDetails userDetails) {
        String email = this.getUsernameFromToken(token);
        final UserAccountJpa userByToken = userAccountRepository.findByEmail(email).orElseThrow();
        final String emailByToken = userByToken.getEmail();

        final UserAccountJpa userByUserDetails = userAccountRepository.findByCpf(userDetails.getUsername()).orElseThrow();
        final String emailByUserDetails = userByUserDetails.getEmail();

        return (emailByToken.equals(emailByUserDetails) && !this.isTokenExpired(token));
    }
}
