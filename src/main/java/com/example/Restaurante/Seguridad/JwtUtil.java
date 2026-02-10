package com.example.Restaurante.Seguridad;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//Clase utilitaria para manejo de JWT

@Service
@RequiredArgsConstructor
public class JwtUtil {
    //Clave secreta para firmar el token
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    //Generar un token JWT
    //@param email email del usuario
    //@return token JWT
    public String generarToken(UsuarioInf usuarioInf) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", usuarioInf.getAuthorities()
                .iterator()
                .next()
                .getAuthority());

        return Jwts.builder()
                .setSubject(usuarioInf.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Extrae el usuario desde el token
    public String extraerUsuario(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

