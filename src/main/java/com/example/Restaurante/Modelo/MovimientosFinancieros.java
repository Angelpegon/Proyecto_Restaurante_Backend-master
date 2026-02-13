package com.example.Restaurante.Modelo;

import com.example.Restaurante.Configuracion.TipoMovimiento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class MovimientosFinancieros {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "fecha")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-0500")
        private LocalDateTime fecha;

        @Enumerated(EnumType.STRING)
        private TipoMovimiento tipo;
        @Column(name = "monto")
        private BigDecimal monto;

        private String descripcion;
    }

