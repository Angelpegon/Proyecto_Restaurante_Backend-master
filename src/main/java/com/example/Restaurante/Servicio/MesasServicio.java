package com.example.Restaurante.Servicio;

import com.example.Restaurante.Modelo.Mesas;
import com.example.Restaurante.Repositorio.MesasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesasServicio {
    @Autowired
    private MesasRepositorio mesasRepositorio;

    public List<Mesas> findAll() {
        return mesasRepositorio.findAll();
    }
    public List<Mesas> verMesasLibres(){
        return mesasRepositorio.verMesasLibres();
    }
    public List<Mesas> findAll(Sort sort) {
        return mesasRepositorio.findAll(sort);
    }

    public Page<Mesas> findAll(Pageable pageable) {
        return mesasRepositorio.findAll(pageable);
    }

    public <S extends Mesas> S save(S entity) {
        return mesasRepositorio.save(entity);
    }

    public Optional<Mesas> findById(Long id) {
        return mesasRepositorio.findById(id);
    }


    public Boolean deleteById(Long id) {
        if (mesasRepositorio.existsById(id)) {
            mesasRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    public void delete(Mesas entity) {
        mesasRepositorio.delete(entity);
    }

}
