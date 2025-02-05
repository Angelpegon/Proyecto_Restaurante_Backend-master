package com.example.Restaurante.Rest;

import com.example.Restaurante.Modelo.Clientes;
import com.example.Restaurante.Servicio.ClientesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/clientes/")
public class ClientesRest {
    @Autowired
    private ClientesServicio clientesServicio;
    @GetMapping
    private ResponseEntity<List<Clientes>> getAllClientes (){
        return ResponseEntity.ok(clientesServicio.findAll());
    }
    @GetMapping("{telefono}")
    private ResponseEntity<List<Clientes>> verClientesPorTelefono (@PathVariable("telefono") String telefono){
        //Logger.getLogger( "Logs").log(Level.INFO,String.valueOf(telefono));
        return ResponseEntity.ok(clientesServicio.verClientesPorTelefono(telefono));
    }
    @PostMapping
    private ResponseEntity<Clientes> saveCliente (@RequestBody Clientes clientes){
        try {
            Clientes clienteGuardado = clientesServicio.save(clientes);
            return ResponseEntity.created(new URI("/clientes/"+ clienteGuardado.getId())).body(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
