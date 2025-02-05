package com.example.Restaurante.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "platosxpedido")
public class PlatosxPedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "platosxpedido_generator")
    @SequenceGenerator(name = "platosxpedido_generator", sequenceName = "platosxpedido_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn (name="id_pedidos")
    private Pedidos pedidos;
    @ManyToOne
    @JoinColumn (name="id_platos")
    private Platos plato;
    private int cantidad;
    private String nota;

}
