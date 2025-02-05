package com.example.Restaurante.Repositorio;

import com.example.Restaurante.Modelo.PlatosxPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlatosxPedidoRepositorio extends JpaRepository <PlatosxPedido,Long> {

}
