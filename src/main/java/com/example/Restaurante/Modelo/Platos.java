package com.example.Restaurante.Modelo;

import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;

@Entity
@Data
@Table(name = "platos")
public class Platos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "platos_generator")
    @SequenceGenerator(name = "platos_generator", sequenceName = "platos_seq", allocationSize = 1)
    private Long id;
    private String nombre;
    private int valor;

}
