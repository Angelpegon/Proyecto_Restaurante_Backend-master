package com.example.Restaurante.Modelo;

import com.example.Restaurante.Configuracion.Estados;
import com.example.Restaurante.Configuracion.TipoDePedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pedidos")
public class Pedidos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidos_generator")
    @SequenceGenerator(name = "pedidos_generator", sequenceName = "pedidos_seq", allocationSize = 1)
    private Long id;
    @Column(name = "fecha")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-0500")
    private LocalDateTime fecha;
    @ManyToOne
    @JoinColumn(name = "id_meseros")
    private Meseros mesero;
    @ManyToOne
    @JoinColumn(name = "id_mesas")
    private Mesas mesa;
    @ManyToOne
    @JoinColumn(name = "id_mediosdepago")
    private MediosdePago mediodepago;
    @ManyToOne
    @JoinColumn(name = "id_clientes")
    private Clientes cliente;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private Estados estado;
    @Enumerated(EnumType.STRING)
    private TipoDePedido tipodepedido;
}