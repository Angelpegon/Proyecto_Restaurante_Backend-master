package com.example.Restaurante.Repositorio;

import com.example.Restaurante.Modelo.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface PedidosRepositorio extends JpaRepository<Pedidos, Long> {
    @Modifying
    @Query("UPDATE Pedidos p SET p.mesero.id = :idMeseros, p.mesa.id = :idMesas WHERE p.id = :pedidoId")
    void updatePedido(@Param("pedidoId") Long pedidoId, @Param("idMeseros") Long idMeseros, @Param("idMesas") Long idMesas);

    @Modifying
    @Query("UPDATE Pedidos p SET p.mesero.id = :idMeseros WHERE p.id = :pedidoId")
    void updateMeseroPedido(@Param("pedidoId") Long pedidoId, @Param("idMeseros") Long idMesero);

    @Modifying
    @Query("UPDATE Pedidos p SET p.mesa.id = :idMesas WHERE p.id = :pedidoId")
    void updateMesaPedido(@Param("pedidoId") Long pedidoId, @Param("idMesas") Long idMesas);

//    @Modifying
//    @Query("UPDATE Pedidos p SET p.estado = 'id_estados' WHERE p.id = :pedidoId")
//    void insertarEstado(@Param("pedidoId") Long pedidoId, @Param("id_estados") Long id_estados);

    @Query("SELECT p FROM Pedidos p WHERE p.estado = 'PENDIENTE' AND p.tipodepedido = 'EN_MESA'")
    List<Pedidos> verPedidosActivosEnMesas();

    @Query("SELECT p FROM Pedidos p WHERE p.estado = 'PENDIENTE' AND p.tipodepedido = 'DOMICILIO'")
    List<Pedidos> verPedidosActivosEnDomicilios();

    @Modifying
    @Query("UPDATE Pedidos p SET p.mediodepago.id = :mediodepago WHERE p.id = :pedidoId")
    void insertarMediodePago(@Param("pedidoId") Long pedidoId, @Param("mediodepago") Long mediodepago);

    @Query("SELECT p FROM Pedidos p WHERE p.fecha BETWEEN :fechainicial AND :fechafinal")
    List<Pedidos> buscarPedidosporFecha(@Param("fechainicial") LocalDateTime fechainicial, @Param("fechafinal") LocalDateTime fechafnal);

    @Query("SELECT p FROM Pedidos p WHERE p.mesa = :text")
    List<Pedidos> buscarPedidosporText(@Param("text") String text);

    @Query("SELECT COUNT(p) FROM Pedidos p WHERE p.fecha >= :inicioDia AND p.fecha < :finDia")
    Long contarPedidosHoy(@Param("inicioDia") LocalDateTime inicioDia, @Param("finDia") LocalDateTime finDia);

    @Query("SELECT COUNT(p) FROM Pedidos p WHERE p.fecha >= :inicioDia AND p.fecha < :finDia AND p.tipodepedido = 'EN_MESA'")
    Long contarPedidosMesaHoy(@Param("inicioDia") LocalDateTime inicioDia, @Param("finDia") LocalDateTime finDia);

    @Query("SELECT COUNT(p) FROM Pedidos p WHERE p.fecha >= :inicioDia AND p.fecha < :finDia AND p.tipodepedido ='DOMICILIO'")
    Long contarPedidosDomiciliosHoy(@Param("inicioDia") LocalDateTime inicioDia, @Param("finDia") LocalDateTime finDia);

    //@Query("SELECT COALESCE(SUM(p.total), 0) FROM Pedidos p WHERE p.fecha >= :inicioDia AND p.fecha < :finDia AND p.estado.id = 2")
    //BigDecimal totalVendidoHoy(@Param("inicioDia") LocalDateTime inicioDia, @Param("finDia") LocalDateTime finDia);

}

