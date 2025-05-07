package com.aro.FechamentoAro.service;

import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aro.FechamentoAro.entities.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {

	@Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("nivel", usuario.getNivel().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extrairClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extrairEmail(String token) {
        return extrairClaims(token).getSubject();
    }

    public Date extrairDataExpiracao(String token) {
        return extrairClaims(token).getExpiration();
    }

    private boolean isRefreshToken(String token) {
        Claims claims = extrairClaims(token);
        return claims.containsKey("refresh") && claims.get("refresh", Boolean.class);
    }

    private boolean isRefreshTokenEndpoint(HttpServletRequest request) {
        return request.getRequestURI().endsWith("/auth/refresh");
    }

    public boolean isTokenValido(String token, HttpServletRequest request) {
        try {
            // Verifica assinatura
            Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token);
            
            // Verifica expiração
            if (extrairDataExpiracao(token).before(new Date())) {
                throw new JwtException("Token expirado");
            }
            
            // Verifica se é um token de refresh sendo usado como access token
            if (isRefreshToken(token) && !isRefreshTokenEndpoint(request)) {
                throw new JwtException("Tipo de token inválido para esta operação");
            }
            
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Token JWT inválido: {}", e.getMessage());
            return false;
        }
    }
}