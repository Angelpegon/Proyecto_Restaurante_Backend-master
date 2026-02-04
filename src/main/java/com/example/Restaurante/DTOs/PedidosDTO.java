package com.example.Restaurante.DTOs;


import com.example.Restaurante.Modelo.*;

import java.time.LocalDateTime;

public class PedidosDTO {
    private long id;
    private LocalDateTime fecha;
    private Meseros mesero;
    private Mesas mesa;
    private Estados estado;
    private MediosdePago mediodepago;
    private TipodePedido tipodepedido;
    private Clientes cliente;

    public PedidosDTO(long id, LocalDateTime fecha, Meseros mesero, Mesas mesa, Estados estado, MediosdePago mediodepago, TipodePedido tipodepedido, Clientes cliente) {
        this.id = id;
        this.fecha = fecha;
        this.mesero = mesero;
        this.mesa = mesa;
        this.estado = estado;
        this.mediodepago = mediodepago;
        this.tipodepedido = tipodepedido;
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Meseros getMesero() {
        return mesero;
    }

    public void setMesero(Meseros mesero) {
        this.mesero = mesero;
    }

    public Mesas getMesa() {
        return mesa;
    }

    public void setMesa(Mesas mesa) {
        this.mesa = mesa;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public MediosdePago getMediodepago() {
        return mediodepago;
    }

    public void setMediodepago(MediosdePago mediodepago) {
        this.mediodepago = mediodepago;
    }

    public TipodePedido getTipodepedido() {
        return tipodepedido;
    }

    public void setTipodepedido(TipodePedido tipodepedido) {
        this.tipodepedido = tipodepedido;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "PedidosDTO{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", mesero=" + mesero +
                ", mesa=" + mesa +
                ", estado=" + estado +
                ", mediodepago=" + mediodepago +
                ", tipodepedido=" + tipodepedido +
                ", cliente=" + cliente +
                '}';
    }
}
