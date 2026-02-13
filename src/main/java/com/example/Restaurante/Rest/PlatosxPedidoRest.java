package com.example.Restaurante.Rest;

import com.example.Restaurante.Modelo.PlatosxPedido;
import com.example.Restaurante.Servicio.MesasServicio;
import com.example.Restaurante.Servicio.PedidosServicio;
import com.example.Restaurante.Servicio.PlatosxPedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/platosxpedido/")
public class PlatosxPedidoRest {
    @Autowired
    private PlatosxPedidoServicio platosxPedidoServicio;
    @Autowired
    private PedidosServicio pedidosServicio;
    @Autowired
    private MesasServicio mesasServicio;

    @GetMapping
    private ResponseEntity<List<PlatosxPedido>> getAllPlatosxPedido() {
        return ResponseEntity.ok(platosxPedidoServicio.findAll());
    }

    @PostMapping
    private ResponseEntity<PlatosxPedido> savePlatoxPedido(@RequestBody PlatosxPedido platosxPedido) {
        try {
            PlatosxPedido platoxPedidoGuardado = platosxPedidoServicio.save(platosxPedido);
            return ResponseEntity.created(new URI("/platosxpedido/" + platoxPedidoGuardado.getId())).body(platoxPedidoGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("{id}")
    private ResponseEntity<List<PlatosxPedido>> findPlatosxPedidoById(@PathVariable("id") Long idPedido) {
        //Logger.getLogger( "Logs").log(Level.INFO,String.valueOf(idPedido));
        return ResponseEntity.ok(platosxPedidoServicio.findPlatosxPedidoById(idPedido));
    }

    @DeleteMapping(value = "delete/{id}")
    private ResponseEntity<Boolean> cancelarPedido(@PathVariable("id") Long id) {
        platosxPedidoServicio.deleteById(id);
        return ResponseEntity.ok(platosxPedidoServicio.findById(id).isEmpty());
    }
}
