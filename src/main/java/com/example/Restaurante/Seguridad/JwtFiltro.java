package com.example.Restaurante.Seguridad;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//Filtro que valida el JWT en cada request
@Component
@RequiredArgsConstructor
public class JwtFiltro extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UsuarioInfServicio usuarioInfServicio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Leer header Authorization
        String authHeader = request.getHeader("Authorization");

        // 2. Validar que exista y tenga Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // 3. Extraer token
        String token = authHeader.substring(7);

        // 4. Extraer usuario del token
        String usuario = jwtUtil.extraerUsuario(token);

        // 5. Verificar que no esté autenticado ya
        if (usuario != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = usuarioInfServicio.loadUserByUsername(usuario);

            // 6. Crear autenticación
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            // 7. Guardar en contexto de seguridad
            SecurityContextHolder.getContext()
                    .setAuthentication(authToken);
        }
        // 8. Continuar la cadena
        filterChain.doFilter(request, response);
    }
}

