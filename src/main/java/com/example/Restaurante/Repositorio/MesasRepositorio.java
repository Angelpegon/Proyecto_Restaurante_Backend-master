package com.example.Restaurante.Repositorio;

import com.example.Restaurante.Modelo.Mesas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public interface MesasRepositorio extends JpaRepository<Mesas,Long> {
    @Query("SELECT m FROM Mesas m WHERE NOT EXISTS (SELECT p FROM Pedidos p WHERE p.mesa.id = m.id AND p.estado.id = 1) ORDER BY m.nombre ASC")
    List<Mesas> verMesasLibres();
}