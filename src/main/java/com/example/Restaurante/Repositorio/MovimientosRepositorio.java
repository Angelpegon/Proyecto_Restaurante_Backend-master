package com.example.Restaurante.Repositorio;

import com.example.Restaurante.Modelo.MovimientosFinancieros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface MovimientosRepositorio extends JpaRepository<MovimientosFinancieros, Long> {
    @Query("SELECT SUM(m.monto) FROM MovimientosFinancieros m WHERE m.tipo = 'INGRESO' AND m.fecha >= :inicioDia AND m.fecha < :finDia")
    BigDecimal totalIngresosHoy(@Param("inicioDia") LocalDateTime inicioDia, @Param("finDia") LocalDateTime finDia);
}
