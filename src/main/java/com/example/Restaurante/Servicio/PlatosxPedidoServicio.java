package com.example.Restaurante.Servicio;

import com.example.Restaurante.Modelo.PlatosxPedido;
import com.example.Restaurante.Repositorio.PlatosxPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlatosxPedidoServicio {
    @Autowired
    private PlatosxPedidoRepositorio platosxPedidoRepositorio;

    public List<PlatosxPedido> findAll() {
        return platosxPedidoRepositorio.findAll();
    }
    public <S extends PlatosxPedido> S save(S entity) {
        return platosxPedidoRepositorio.save(entity);
    }
    public List<PlatosxPedido> findPlatosxPedidoById (Long id){
        List<PlatosxPedido> PlatosxPedidoRespuesta= new ArrayList<>();
        List<PlatosxPedido> PlatosxPedido= platosxPedidoRepositorio.findAll();
        for (int i=0; i<PlatosxPedido.size(); i++) {
            if (PlatosxPedido.get(i).getPedidos().getId().equals(id)) {
                PlatosxPedidoRespuesta.add(PlatosxPedido.get(i));
            }
        }
        return PlatosxPedidoRespuesta;
    }

    public Optional<PlatosxPedido> findById(Long id) {
        return platosxPedidoRepositorio.findById(id);
    }
    public Boolean deleteById(Long id) {
        if (platosxPedidoRepositorio.existsById(id)) {
            platosxPedidoRepositorio.deleteById(id);
            return true;
        }
        return false;
    }
}
