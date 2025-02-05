package com.example.Restaurante.Rest;

import com.example.Restaurante.Modelo.Estados;
import com.example.Restaurante.Servicio.EstadosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados/")
public class EstadosRest {
    @Autowired
    private EstadosServicio estadosServicio;

    @GetMapping
    private ResponseEntity<List<Estados>> getAllMeseros (){
        return ResponseEntity.ok(estadosServicio.findAll());
    }
}
