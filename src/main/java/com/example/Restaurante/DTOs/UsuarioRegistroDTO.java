package com.example.Restaurante.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//DTO utilizado para registrar un usuario
public class UsuarioRegistroDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "Usuario inválido")
    private String usuario;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
