package com.example.Restaurante.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "clientes")
public class Clientes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientes_generator")
    @SequenceGenerator(name = "clientes_generator", sequenceName = "clientes_seq", allocationSize = 1)
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
}
