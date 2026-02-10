package com.example.Restaurante.Seguridad;

import com.example.Restaurante.Modelo.Usuarios;
import com.example.Restaurante.Repositorio.UsuariosRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InicializarDatos implements CommandLineRunner {
    private final UsuariosRepositorio usuariosRepositorio;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuariosRepositorio.count() == 0) {
            Usuarios admin = new Usuarios();
            admin.setUsuario("admin");
            admin.setPassword(
                    passwordEncoder.encode("12345")
            );
            admin.setRol(Rol.ADMIN);
            usuariosRepositorio.save(admin);
            System.out.println("Usuario admin creado");
        }
    }
}
