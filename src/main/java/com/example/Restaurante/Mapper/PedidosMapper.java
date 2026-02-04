package com.example.Restaurante.Mapper;

import com.example.Restaurante.DTOs.PedidosDTO;
import com.example.Restaurante.Modelo.Pedidos;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidosMapper {
    PedidosDTO toDto(Pedidos pedidos);

    Pedidos toEntity(PedidosDTO dto);
}
