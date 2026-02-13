package com.example.Restaurante.DTOs;

import java.util.List;

public class CrearPedidoDTO {
    private Long mesaId;
    private Long meseroId;
    private Long tipoPedidoId;

    private List<DetallePedidoDTO> detalles;
}
