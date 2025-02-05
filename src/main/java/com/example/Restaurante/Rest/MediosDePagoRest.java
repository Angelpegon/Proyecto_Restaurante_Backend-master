package com.example.Restaurante.Rest;

import com.example.Restaurante.Modelo.MediosdePago;
import com.example.Restaurante.Servicio.MediosDePagoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/mediosdepago/")
public class MediosDePagoRest {
    @Autowired
    private MediosDePagoServicio mediosDePagoServicio;
    @GetMapping
    private ResponseEntity<List<MediosdePago>> getAllMediosdePago (){
        return ResponseEntity.ok(mediosDePagoServicio.findAll());
    }
    @PostMapping
    private ResponseEntity<MediosdePago> saveMediosdePago (@RequestBody MediosdePago mediosdePago){
        try {
            MediosdePago mediosdePagoGuardado = mediosDePagoServicio.save(mediosdePago);
            return ResponseEntity.created(new URI("/mediosdepago/"+ mediosdePagoGuardado.getId())).body(mediosdePagoGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping(value = "delete/{id}")
    private ResponseEntity<Boolean> deleteMediosdePago (@PathVariable("id") Long id){
        mediosDePagoServicio.deleteById(id);
        return ResponseEntity.ok(mediosDePagoServicio.findById(id).isEmpty());
    }
}
