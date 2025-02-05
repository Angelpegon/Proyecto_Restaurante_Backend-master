package com.example.Restaurante.Modelo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class Pedido implements Serializable {
    private Pedidos pedidos;
    private ArrayList <PlatosxPedido> platosxPedido = new ArrayList ();
}
