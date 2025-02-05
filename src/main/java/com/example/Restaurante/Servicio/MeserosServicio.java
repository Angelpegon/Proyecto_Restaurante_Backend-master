package com.example.Restaurante.Servicio;

import com.example.Restaurante.Modelo.Meseros;
import com.example.Restaurante.Repositorio.MeserosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeserosServicio {
    @Autowired
    private MeserosRepositorio meserosRepositorio;

    public List<Meseros> findAll() {
        return meserosRepositorio.findAll();
    }
    public List<Meseros> findAll(Sort sort) {
        return meserosRepositorio.findAll(sort);
    }
    public Page<Meseros> findAll(Pageable pageable) {
        return meserosRepositorio.findAll(pageable);
    }
    public <S extends Meseros> S save(S entity) {
        return meserosRepositorio.save(entity);
    }
    public Optional<Meseros> findById(Long id) {
        return meserosRepositorio.findById(id);
    }
    public Boolean deleteById(Long id) {
        if (meserosRepositorio.existsById(id)) {
            meserosRepositorio.deleteById(id);
            return true;
        }
        return false;
    }
    public void delete(Meseros entity) {
        meserosRepositorio.delete(entity);
    }
}
