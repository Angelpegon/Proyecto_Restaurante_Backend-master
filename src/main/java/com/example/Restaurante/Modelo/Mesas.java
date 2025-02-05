package com.example.Restaurante.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "mesas")
public class Mesas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mesas_generator")
    @SequenceGenerator(name = "mesas_generator", sequenceName = "mesas_seq", allocationSize = 1)
    private Long id;
    private String nombre;
}