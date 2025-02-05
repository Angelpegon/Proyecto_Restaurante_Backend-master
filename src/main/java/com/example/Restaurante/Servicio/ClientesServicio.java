package com.example.Restaurante.Servicio;

import com.example.Restaurante.Modelo.Clientes;
import com.example.Restaurante.Repositorio.ClientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesServicio {
    @Autowired
    private ClientesRepositorio clientesRepositorio;

    public List<Clientes> verClientesPorTelefono(String telefono) {
        return clientesRepositorio.verClientesPorTelefono(telefono);
    }
    public List<Clientes> findAll() {
        return clientesRepositorio.findAll();
    }
    public Optional<Clientes> findById(Long id) {
        return clientesRepositorio.findById(id);
    }
    public <S extends Clientes> S save(S entity) {
        return clientesRepositorio.save(entity);
    }
}
