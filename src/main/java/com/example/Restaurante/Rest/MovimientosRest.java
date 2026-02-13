package com.example.Restaurante.Rest;

import com.example.Restaurante.Servicio.MovimientosServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movimientos/")
public class MovimientosRest {

    private final MovimientosServicio movimientosServicio;

    @GetMapping("totalIngresosHoy")
    public ResponseEntity<BigDecimal> totalIngresosHoy(){
        return ResponseEntity.ok(movimientosServicio.totalIngresosHoy());
    }

}
