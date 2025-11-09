package br.com.bradesco.safeboleto.services;

import br.com.bradesco.safeboleto.exception.JwtTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        Instant now = Instant.now();

        return Jwts.builder()
                .issuer("safe-boleto-api")
                .subject(principal.getUsername())
                .issuedAt(java.util.Date.from(now))
                .expiration(java.util.Date.from(now.plus(expiration, ChronoUnit.MILLIS)))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String getSubjectFromToken(String token) {
        try {
            return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getSubject();
        } catch (Exception e) {
            throw new JwtTokenException("Token JWT inválido ou expirado: " + e.getMessage());
        }
    }
}