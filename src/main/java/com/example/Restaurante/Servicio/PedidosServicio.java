package com.example.Restaurante.Servicio;

import com.example.Restaurante.Modelo.Pedidos;
import com.example.Restaurante.Modelo.PlatosxPedido;
import com.example.Restaurante.Repositorio.PedidosRepositorio;
import com.example.Restaurante.Repositorio.PlatosxPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidosServicio {
    @Autowired
    private PedidosRepositorio pedidosRepositorio;
    private PlatosxPedidoRepositorio platosxPedidoRepositorio;

    public PedidosServicio() {
    }

    public List<Pedidos> findAll() {
        return pedidosRepositorio.findAll();
    }
    public List<Pedidos> verPedidosActivosEnMesas(){
        return pedidosRepositorio.verPedidosActivosEnMesas();
    }
    public List<Pedidos> verPedidosActivosEnDomicilios(){
        return pedidosRepositorio.verPedidosActivosEnDomicilios();
    }
    public List<Pedidos> buscarPedidosporFecha(LocalDateTime fechainicial, LocalDateTime fechafinal){
        return pedidosRepositorio.buscarPedidosporFecha(fechainicial , fechafinal);
    }
    public List<Pedidos> buscarPedidosporText(String text ){
        return pedidosRepositorio.buscarPedidosporText(text);
    }

    public List<Pedidos> findAll(Sort sort) {
        return pedidosRepositorio.findAll(sort);
    }
    public Page<Pedidos> findAll(Pageable pageable) {

        return pedidosRepositorio.findAll(pageable);
    }
    public <S extends Pedidos> S save(S entity) {
        return pedidosRepositorio.save(entity);
    }
    public boolean saveall(List<PlatosxPedido> list, Pedidos entity) {
        Pedidos p = pedidosRepositorio.save(entity);
        long id = p.getId();
        for (PlatosxPedido pp: list
             ) {
            pp.setPedidos(p);
            platosxPedidoRepositorio.save(pp);
        }
        return true;
    }
    public List<Pedidos> findPedidosById (Long id){
        List<Pedidos> pedidosRespuesta= new ArrayList<>();
        List<Pedidos> pedidos= pedidosRepositorio.findAll();
        for (Pedidos pedido : pedidos) {
            if (pedido.getId().equals(id)) {
                pedidosRespuesta.add(pedido);
            }
        }
        return pedidosRespuesta;
    }
    public void  insertarMediodePago (Long id_Pedido, Long mediodepago){
        pedidosRepositorio.insertarMediodePago(id_Pedido,mediodepago);
    }
    public void updatePedido( Long id_Pedido, Long id_Mesero, Long id_Mesa){
        pedidosRepositorio.updatePedido(id_Pedido,id_Mesero,id_Mesa);
    }
    public void updateMeseroPedido( Long id_Pedido, Long id_Mesero){
        pedidosRepositorio.updateMeseroPedido(id_Pedido,id_Mesero);
    }
    public void updateMesaPedido( Long id_Pedido, Long id_Mesa){
        pedidosRepositorio.updateMesaPedido(id_Pedido,id_Mesa);
    }
    public void  insertarEstado (Long id_Pedido, Long id_Estado){
        pedidosRepositorio.insertarEstado(id_Pedido,id_Estado);
    }
    public Optional<Pedidos> findById(Long id) {
        return pedidosRepositorio.findById(id);
    }
    public Boolean deleteById(Long id) {
        if (pedidosRepositorio.existsById(id)) {
            pedidosRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    public void delete(Pedidos entity) {
        pedidosRepositorio.delete(entity);
    }
}
