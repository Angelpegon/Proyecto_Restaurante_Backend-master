package com.example.Restaurante.Repositorio;

import com.example.Restaurante.Modelo.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesRepositorio extends JpaRepository<Clientes,Long> {
    @Query("SELECT c FROM Clientes c WHERE c.telefono = :telefono")
    List<Clientes> verClientesPorTelefono(@Param("telefono") String telefono);
}
