package com.example.Restaurante.Servicio;

import com.example.Restaurante.Modelo.Estados;
import com.example.Restaurante.Modelo.TipodePedido;
import com.example.Restaurante.Repositorio.TiposdePedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TiposdePedidoServicio {
    @Autowired
    private TiposdePedidoRepositorio tiposdePedidoRepositorio;

    public List<TipodePedido> findAll() {
        return tiposdePedidoRepositorio.findAll();
    }

}
