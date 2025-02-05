package com.example.Restaurante.Servicio;

import com.example.Restaurante.Modelo.MediosdePago;
import com.example.Restaurante.Repositorio.MediosDePagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediosDePagoServicio {
    @Autowired
    private MediosDePagoRepositorio mediosDePagoRepositorio;

    public List<MediosdePago> findAll() {
        return mediosDePagoRepositorio.findAll();
    }
    public Optional<MediosdePago> findById(Long id) {
        return mediosDePagoRepositorio.findById(id);
    }
    public <S extends MediosdePago> S save(S entity) {
        return mediosDePagoRepositorio.save(entity);
    }
    public Boolean deleteById(Long id) {
        if (mediosDePagoRepositorio.existsById(id)) {
            mediosDePagoRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

}
