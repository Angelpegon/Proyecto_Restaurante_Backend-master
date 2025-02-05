package com.example.Restaurante.Servicio;

import com.example.Restaurante.Modelo.Estados;
import com.example.Restaurante.Repositorio.EstadosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadosServicio {
    @Autowired
    private EstadosRepositorio estadosRepositorio;

    public List<Estados> findAll() {
        return estadosRepositorio.findAll();
    }

}
