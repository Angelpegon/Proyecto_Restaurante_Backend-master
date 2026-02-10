package com.example.Restaurante.Repositorio;

import com.example.Restaurante.Modelo.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepositorio extends JpaRepository<Usuarios, Long> {

     //Busca un usuario por su usuario
     //@param usuario
     //@return usuario si existe
     Optional<Usuarios> findByUsuario(String usuario);

    //Verifica si un email ya existe
    boolean existsByUsuario(String usuario);
}
