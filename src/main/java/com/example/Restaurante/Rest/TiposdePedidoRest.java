package com.example.Restaurante.Rest;

import com.example.Restaurante.Configuracion.TipoDePedido;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/tiposdepedido/")
public class TiposdePedidoRest {

    @GetMapping
    public List<String> getAllTiposdePedido() {
        return Arrays.stream(TipoDePedido.values())
                .map(Enum::name)
                .toList();
    }
}
