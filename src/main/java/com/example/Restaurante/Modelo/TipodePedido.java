package com.example.Restaurante.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "tiposdepedido")
public class TipodePedido implements Serializable {
    @Id
    private Long id;
    private String nombre;
}
