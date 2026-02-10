package com.example.Restaurante.Seguridad;

import com.example.Restaurante.Modelo.Usuarios;
import com.example.Restaurante.Repositorio.UsuariosRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioInfServicio implements UserDetailsService {
    private final UsuariosRepositorio usuariosRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuarios usuarios = usuariosRepositorio.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new UsuarioInf(usuarios);
    }
}
