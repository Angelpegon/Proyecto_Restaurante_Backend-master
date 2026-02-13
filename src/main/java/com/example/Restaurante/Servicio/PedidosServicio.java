package com.example.Restaurante.Servicio;

import com.example.Restaurante.Configuracion.Estados;
import com.example.Restaurante.Configuracion.TipoDePedido;
import com.example.Restaurante.Excepciones.PedidoNotFoundException;
import com.example.Restaurante.Modelo.Clientes;
import com.example.Restaurante.Modelo.Pedido;
import com.example.Restaurante.Modelo.Pedidos;
import com.example.Restaurante.Modelo.PlatosxPedido;
import com.example.Restaurante.Repositorio.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidosServicio {

    private final PedidosRepositorio pedidosRepositorio;
    private final PlatosxPedidoRepositorio platosxPedidoRepositorio;
    private final MesasRepositorio mesasRepositorio;
    private final MeserosRepositorio meserosRepositorio;
    private final MediosDePagoRepositorio mediosDePagoRepositorio;
    private final PlatosxPedidoServicio platosxPedidoServicio;

    public List<Pedidos> findAll() {
        return pedidosRepositorio.findAll();
    }

    public List<Pedidos> findPedidoById(Long id) {
        List<Pedidos> PedidosRespuesta = new ArrayList<>();
        List<Pedidos> Pedidos = pedidosRepositorio.findAll();
        for (int i = 0; i < Pedidos.size(); i++) {
            if (Pedidos.get(i).getId().equals(id)) {
                PedidosRespuesta.add(Pedidos.get(i));
            }
        }
        return PedidosRespuesta;
    }

    @Transactional
    public void cambiarEstado(Long pedidoId, Estados nuevoEstado) {
        Pedidos pedido = pedidosRepositorio.findById(pedidoId).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        Estados estadoAnterior = pedido.getEstado();

//       // 1️⃣ Validar transición (opcional pero profesional)
//        validarTransicion(estadoAnterior, nuevoEstado);

        // 2️⃣ Cambiar estado
        pedido.setEstado(nuevoEstado);

        pedidosRepositorio.save(pedido);

//        // 3️⃣ Si pasó a PAGADO → registrar ingreso
//        if (nuevoEstado == EstadoPedido.PAGADO) {
//            registrarIngreso(pedido);
//        }
    }

    /**
     * Crea un pedido completo con sus platos
     * Aplica reglas de negocio necesarias
     */
    @Transactional
    public Pedido crearPedidoCompleto(Pedido request) {

        Pedidos pedidos = request.getPedidos();

        if (pedidos == null) {
            throw new RuntimeException("El pedido no puede ser nulo");
        }

        // 1️⃣ Fecha automática
        pedidos.setFecha(LocalDateTime.now());

        // 2️⃣ Regla: si es en mesa y no tiene cliente → cliente genérico
        if (pedidos.getTipodepedido() == TipoDePedido.EN_MESA &&
                (pedidos.getCliente() == null ||
                        pedidos.getCliente().getId() == null)) {
            Clientes clienteGenerico = new Clientes();
            clienteGenerico.setId(1L);
            pedidos.setCliente(clienteGenerico);
        }

        // 3️⃣ Guardar pedido
        Pedidos pedidoGuardado = save(pedidos);

        // 4️⃣ Cambiar estado
        cambiarEstado(pedidoGuardado.getId(), Estados.PENDIENTE);

        // 5️⃣ Guardar platos asociados
        if (request.getPlatosxPedido() != null) {
            request.getPlatosxPedido().forEach(platoDetalle -> {
                platoDetalle.setId(null); // evitar conflictos
                platoDetalle.setPedidos(pedidoGuardado);
                platosxPedidoServicio.savePlatoxPedido(platoDetalle);
            });
        }
        Pedido response = new Pedido();
        response.setPedidos(pedidoGuardado);
        response.setPlatosxPedido(request.getPlatosxPedido());
        return response;
    }

    public void editPedido(Pedidos pedidos) {
        if (pedidos.getMesero() != null && pedidos.getMesa() != null) {
            updatePedido(pedidos.getId(), pedidos.getMesero().getId(), pedidos.getMesa().getId());
        }
        if (pedidos.getMesero() != null && pedidos.getMesa() == null) {
            updateMeseroPedido(pedidos.getId(), pedidos.getMesero().getId());
        }
        if (pedidos.getMesero() == null && pedidos.getMesa() != null) {
            updateMesaPedido(pedidos.getId(), pedidos.getMesa().getId());
        }
    }

    public void addPlatosxPedido (Pedido pedido){
        for (int i = 0; i < pedido.getPlatosxPedido().size(); i++) {
            pedido.getPlatosxPedido().get(i).setPedidos(pedido.getPedidos());
            platosxPedidoServicio.savePlatoxPedido(pedido.getPlatosxPedido().get(i));
        }
    }
//    @Transactional
//    public Pedidos crearPedido(Pedidos pedidos) {
//
//        DetallePedidoDTO detallePedidoDTO;
//        Pedidos pedido = new Pedidos();
//
//        pedido.setFecha(LocalDateTime.now());
//
//        pedido.setMesa(mesasRepositorio.findById(pedidos.getMesa().getId())
//                .orElseThrow(() -> new RuntimeException("Mesa no encontrada")));
//
//        pedido.setMesero(meserosRepositorio.findById(pedidos.getMesero().getId())
//                .orElseThrow(() -> new RuntimeException("Mesero no encontrado")));
//
//        pedido.setMediodepago(mediosDePagoRepositorio.findById(pedidos.getMediodepago().getId())
//                .orElseThrow(() -> new RuntimeException("Medio de pago no encontrado")));
//
//        pedido.setEstado(Estados.PENDIENTE);
//
//        pedido = pedidosRepositorio.save(pedido);
//
//        BigDecimal total = BigDecimal.ZERO;
//
//        for (PlatosxPedido platosxPedido : pedido.getDetalles()) {
//
//            Platos plato = platoRepository.findById(detalleDTO.getPlatoId())
//                    .orElseThrow(() -> new RuntimeException("Plato no encontrado"));
//
//            PlatosxPedido detalle = new PlatosxPedido();
//
//            detalle.setPedidos(pedido);
//            detalle.setPlato(plato);
//            detalle.setCantidad(detalleDTO.getCantidad());
//
//            BigDecimal subtotal = plato.getPrecio()
//                    .multiply(BigDecimal.valueOf(detalleDTO.getCantidad()));
//
//            detalle.setSubtotal(subtotal);
//
//            total = total.add(subtotal);
//
//            platosxPedidoRepository.save(detalle);
//        }
//
//        pedido.setTotal(total);
//
//        return pedido;
//    }


    public Optional<Pedidos> findById(Long id) {
        new PedidoNotFoundException(id);
        return pedidosRepositorio.findById(id);
    }

    private LocalDateTime inicioDia() {
        return LocalDate.now().atStartOfDay();
    }

    private LocalDateTime finDia() {
        return LocalDate.now().plusDays(1).atStartOfDay();
    }

    public Long totalPedidosHoy() {
        return pedidosRepositorio.contarPedidosHoy(inicioDia(), finDia());
    }

    public Long totalPedidosDomiciliosHoy() {
        return pedidosRepositorio.contarPedidosDomiciliosHoy(inicioDia(), finDia());
    }

    public Long totalPedidosMesaHoy() {
        return pedidosRepositorio.contarPedidosMesaHoy(inicioDia(), finDia());
    }

    public Long totalPedidos() {
        return pedidosRepositorio.count();
    }


    public List<Pedidos> verPedidosActivosEnMesas() {
        return pedidosRepositorio.verPedidosActivosEnMesas();
    }

    public List<Pedidos> verPedidosActivosEnDomicilios() {
        return pedidosRepositorio.verPedidosActivosEnDomicilios();
    }

    public List<Pedidos> buscarPedidosporFecha(LocalDateTime fechainicial, LocalDateTime fechafinal) {
        return pedidosRepositorio.buscarPedidosporFecha(fechainicial, fechafinal);
    }

    public List<Pedidos> buscarPedidosporText(String text) {
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
        for (PlatosxPedido pp : list) {
            pp.setPedidos(p);
            platosxPedidoRepositorio.save(pp);
        }
        return true;
    }

    public void insertarMediodePago(Long id_Pedido, Long mediodepago) {
        pedidosRepositorio.insertarMediodePago(id_Pedido, mediodepago);
    }

    public void updatePedido(Long id_Pedido, Long id_Mesero, Long id_Mesa) {
        pedidosRepositorio.updatePedido(id_Pedido, id_Mesero, id_Mesa);
    }

    public void updateMeseroPedido(Long id_Pedido, Long id_Mesero) {
        pedidosRepositorio.updateMeseroPedido(id_Pedido, id_Mesero);
    }

    public void updateMesaPedido(Long id_Pedido, Long id_Mesa) {
        pedidosRepositorio.updateMesaPedido(id_Pedido, id_Mesa);
    }

//    public void insertarEstado(Long id_Pedido, Long id_Estado) {
//        pedidosRepositorio.insertarEstado(id_Pedido, id_Estado);
//    }

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
