package com.example.Restaurante.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Entity
@Data
@Table(name = "mediosdepago")
public class MediosdePago implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mediosdepago_generator")
    @SequenceGenerator(name = "mediosdepago_generator", sequenceName = "mediosdepago_seq", allocationSize = 1)
    private Long id;
    private String nombre;
}
