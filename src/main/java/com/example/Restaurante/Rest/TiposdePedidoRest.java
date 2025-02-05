package com.example.Restaurante.Rest;

import com.example.Restaurante.Modelo.Estados;
import com.example.Restaurante.Modelo.TipodePedido;
import com.example.Restaurante.Servicio.TiposdePedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tiposdepedido/")
public class TiposdePedidoRest {
    @Autowired
    private TiposdePedidoServicio tiposdePedidoServicio;

    @GetMapping
    private ResponseEntity<List<TipodePedido>> getAllTiposdePedido (){
        return ResponseEntity.ok(tiposdePedidoServicio.findAll());
    }
}
