package com.example.Restaurante.Rest;

import com.example.Restaurante.Modelo.Fechas;
import com.example.Restaurante.Modelo.Pedidos;
import com.example.Restaurante.Servicio.MesasServicio;
import com.example.Restaurante.Servicio.PedidosServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Tag(name = "Pedidos", description = "Gestión de Pedidos del restaurante")
@RestController
@RequestMapping("/pedidos/")
public class PedidosRest {
    @Autowired
    private PedidosServicio pedidosServicio;
    @Autowired
    private MesasServicio mesasServicio;

    @GetMapping
    public ResponseEntity<List<Pedidos>> getAllPedidos(){
        return ResponseEntity.ok(pedidosServicio.findAll());
    }

    @GetMapping(value = "verPedidosActivosEnMesas")
    private ResponseEntity<List<Pedidos>> verPedidosActivosEnMesas() {
        return ResponseEntity.ok(pedidosServicio.verPedidosActivosEnMesas());
    }

    @GetMapping(value = "verPedidosActivosEnDomicilios")
    private ResponseEntity<List<Pedidos>> verPedidosActivosEnDomicilios() {
        return ResponseEntity.ok(pedidosServicio.verPedidosActivosEnDomicilios());
    }

    @PostMapping(value = "buscarPedidosporFecha")
    private ResponseEntity<List<Pedidos>> buscarPedidosporFecha(@RequestBody Fechas fechas) {
        //Logger.getLogger( "Logs").log(Level.INFO,String.valueOf(fechas));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            // Parseamos la cadena para obtener el objeto Date
            LocalDateTime fechainicio = LocalDateTime.parse(fechas.getFechaInicial() + " 00:00:00", dateFormat);
            LocalDateTime fechafinal = LocalDateTime.parse(fechas.getFechaFinal() + " 23:59:59", dateFormat);
            // Retorna la fecha convertida
            return ResponseEntity.ok(pedidosServicio.buscarPedidosporFecha(fechainicio, fechafinal));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "buscarPedidosporText")
    private ResponseEntity<List<Pedidos>> buscarPedidosporText(@RequestBody String text) {
        //Logger.getLogger( "Logs").log(Level.INFO,String.valueOf(text));
        try {
            return ResponseEntity.ok(pedidosServicio.buscarPedidosporText(text));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "finalizarPedido")
    private ResponseEntity<Pedidos> finalizarPedido(@RequestBody Pedidos pedidos) {
        pedidosServicio.insertarMediodePago(pedidos.getId(), pedidos.getMediodepago().getId());
        pedidosServicio.insertarEstado(pedidos.getId(), 2l);
        try {
            return ResponseEntity.created(new URI("/pedidos/" + pedidos.getId())).body(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("{id}")
    private ResponseEntity<List<Pedidos>> findPedidoById(@PathVariable("id") Long idPedido) {
        return ResponseEntity.ok(pedidosServicio.findPedidoById(idPedido));
    }

    @PostMapping(value = "editPedido")
    private ResponseEntity<Pedidos> editPedido(@RequestBody Pedidos pedidos) {
        //Logger.getLogger( "Logs").log(Level.INFO,String.valueOf(pedidos));
        if (pedidos.getMesero() != null && pedidos.getMesa() != null) {
            pedidosServicio.updatePedido(pedidos.getId(), pedidos.getMesero().getId(), pedidos.getMesa().getId());
        }
        if (pedidos.getMesero() != null && pedidos.getMesa() == null) {
            pedidosServicio.updateMeseroPedido(pedidos.getId(), pedidos.getMesero().getId());
        }
        if (pedidos.getMesero() == null && pedidos.getMesa() != null) {
            pedidosServicio.updateMesaPedido(pedidos.getId(), pedidos.getMesa().getId());
        }
        try {
            return ResponseEntity.created(new URI("/pedidos/" + pedidos.getId())).body(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @Operation(summary = "Crear Pedido", description = "Crea un nuevo pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Pedidos> savePedido(@Valid @RequestBody Pedidos pedidos) throws URISyntaxException {
        Pedidos pedidoGuardado = pedidosServicio.save(pedidos);
        return ResponseEntity
                .created(new URI("/pedidos/" + pedidoGuardado.getId()))
                .body(pedidoGuardado);
    }


    @DeleteMapping(value = "delete/{id}")
    private ResponseEntity<Boolean> cancelarPedido(@PathVariable("id") Long id) {
        pedidosServicio.insertarEstado(id, 3l);
        return ResponseEntity.ok(pedidosServicio.findById(id).isEmpty());
    }

    @GetMapping("totalPedidos")
    private ResponseEntity<Long> totalPedidos() {
        Long totalPedidos = pedidosServicio.totalPedidos();
        return ResponseEntity.ok(totalPedidos);
    }

    @GetMapping("totalPedidosHoy")
    private ResponseEntity<Long> totalPedidosHoy() {
        return ResponseEntity.ok(pedidosServicio.totalPedidosHoy());
    }

    @GetMapping("totalPedidosDomiciliosHoy")
    private ResponseEntity<Long> totalPedidosDomiciliosHoy() {
        return ResponseEntity.ok(pedidosServicio.totalPedidosDomiciliosHoy());
    }
    @GetMapping("totalPedidosMesaHoy")
    private ResponseEntity<Long> totalPedidosMesaHoy() {
        return ResponseEntity.ok(pedidosServicio.totalPedidosMesaHoy());
    }
}
