package com.example.Restaurante.Modelo;

import com.example.Restaurante.Seguridad.Rol;
import jakarta.persistence.*;
import lombok.*;


//Entidad que representa un usuario del sistema.
//Cada usuario tendrá sus propios ingresos, gastos y deudas.
@Entity
@Table(name = "usuarios")
@Data
public class Usuarios {

    //Identificador único del usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Usuario (único)
    @Column(nullable = false, unique = true)
    private String usuario;

    //Contraseña encriptada
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
