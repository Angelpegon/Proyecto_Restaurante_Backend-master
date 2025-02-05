package com.example.Restaurante.Rest;

import com.example.Restaurante.Modelo.Meseros;
import com.example.Restaurante.Servicio.MeserosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/meseros/")
public class MeserosRest {
    @Autowired
    private MeserosServicio meserosServicio;
    @GetMapping
    private ResponseEntity<List<Meseros>> getAllMeseros (){
        return ResponseEntity.ok(meserosServicio.findAll());
    }

    @PostMapping
    private ResponseEntity<Meseros> savePlato (@RequestBody Meseros mesero){
        try {
            Meseros meseroGuardado = meserosServicio.save(mesero);
            return ResponseEntity.created(new URI("/meseros/"+meseroGuardado.getId())).body(meseroGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping (value = "delete/{id}")
    private ResponseEntity<Boolean> deletePlato (@PathVariable ("id") Long id){
        meserosServicio.deleteById(id);
        return ResponseEntity.ok(meserosServicio.findById(id).isEmpty());
    }
}
