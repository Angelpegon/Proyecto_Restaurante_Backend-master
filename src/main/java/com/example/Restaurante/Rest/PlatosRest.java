package com.example.Restaurante.Rest;

import com.example.Restaurante.Modelo.Platos;
import com.example.Restaurante.Servicio.PlatosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/platos/")
public class PlatosRest {

    @Autowired
    private PlatosServicio platosServicio;

    @GetMapping
    private ResponseEntity<List<Platos>> getAllPlatos (){
        return ResponseEntity.ok(platosServicio.findAll());
    }

    @PostMapping (value="savePlato")
    private ResponseEntity<Platos> savePlato (@RequestBody Platos plato){
        Logger.getLogger( "Logs").log(Level.INFO,String.valueOf(plato));
        try {
            Platos platoGuardado = platosServicio.save(plato);
            return ResponseEntity.created(new URI("/platos/"+platoGuardado.getId())).body(platoGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping (value = "delete/{id}")
    private ResponseEntity<Boolean> deletePlato (@PathVariable ("id") Long id){
        platosServicio.deleteById(id);
        return ResponseEntity.ok(platosServicio.findById(id).isEmpty());
    }
}