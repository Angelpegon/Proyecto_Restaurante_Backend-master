package com.example.Restaurante.Servicio;

import com.example.Restaurante.DTOs.LoginRequestDTO;
import com.example.Restaurante.DTOs.LoginResponseDTO;
import com.example.Restaurante.Seguridad.JwtUtil;
import com.example.Restaurante.Seguridad.UsuarioInf;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServicio {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginResponseDTO login(LoginRequestDTO request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsuario(),
                        request.getPassword()
                )
        );

        UsuarioInf usuarioInf = (UsuarioInf) authentication.getPrincipal();

        String token = jwtUtil.generarToken(usuarioInf);

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setUsername(usuarioInf.getUsername());
        response.setRol(
                usuarioInf.getAuthorities()
                        .iterator()
                        .next()
                        .getAuthority()
        );

        return response;
    }
}
