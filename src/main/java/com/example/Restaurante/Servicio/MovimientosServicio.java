package com.example.Restaurante.Servicio;

import com.example.Restaurante.Repositorio.MovimientosRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MovimientosServicio {
    private final MovimientosRepositorio movimientosRepositorio;

    private LocalDateTime inicioDia() {
        return LocalDate.now().atStartOfDay();
    }

    private LocalDateTime finDia() {
        return LocalDate.now().plusDays(1).atStartOfDay();
    }

    public BigDecimal totalIngresosHoy() {
        return movimientosRepositorio.totalIngresosHoy(inicioDia(),finDia());
    }
}
