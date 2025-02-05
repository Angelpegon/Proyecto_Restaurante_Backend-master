package com.example.Restaurante.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "estados")
public class Estados implements Serializable {
    @Id
    private Long id;
    private String nombre;
}
