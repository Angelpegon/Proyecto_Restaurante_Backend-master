package com.example.Restaurante.Servicio;

import com.example.Restaurante.Modelo.Platos;
import com.example.Restaurante.Repositorio.PlatosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatosServicio {

    @Autowired
    private PlatosRepositorio platosRepositorio;

    public List<Platos> findAll() {
        return platosRepositorio.findAll();
    }

    public List<Platos> findAll(Sort sort) {
        return platosRepositorio.findAll(sort);
    }


    public Page<Platos> findAll(Pageable pageable) {
        return platosRepositorio.findAll(pageable);
    }

    public <S extends Platos> S save(S entity) {
        return platosRepositorio.save(entity);
    }

    public Optional<Platos> findById(Long id) {
        return platosRepositorio.findById(id);
    }


    public Boolean deleteById(Long id) {
        if (platosRepositorio.existsById(id)) {
            platosRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    public void delete(Platos entity) {
        platosRepositorio.delete(entity);
    }




}