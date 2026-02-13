package com.example.Restaurante.Rest;

import com.example.Restaurante.Configuracion.Estados;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/estados/")
public class EstadosRest {

        @GetMapping
        public List<String> getAllEstados() {
            return Arrays.stream(Estados.values())
                    .map(Enum::name)
                    .toList();
        }
    }
