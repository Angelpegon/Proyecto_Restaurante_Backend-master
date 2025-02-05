package com.example.Restaurante.Modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "meseros")

public class Meseros implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meseros_generator")
    @SequenceGenerator(name = "meseros_generator", sequenceName = "meseros_seq", allocationSize = 1)
    private Long id;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String fechaIngreso;
    private String fechaNacimiento;
    private String direccion;
    private String telefono;
    private String sexo;
    private String estado;
}
