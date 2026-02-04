## Entidades
- Clientes
- Estados
- Fechas
- MediosdePago
- Mesas
- Meseros
- Pedido
- Pedidos
- Platos
- PlatosxPedido
- TipodePedido

## Endpoints actuales
- GET /getAllPedidos
- POST /savePedido

## Flujo principal
Rest -> Servicio -> Repositorio -> DTOs

## Observaciones
- Controllers sin lógica
- Entidades expuestas directamente
- Métodos cortos
- Uso de DTOs para algunos endpoint

## Problemas detectados
1. Falta Autenticacion
2. Endpoint Y retorna entidad JPA
3. Falta validación

## Cosas bien hechas
1. Uso correcto de JPA
2. Buen uso de query
3. Relación entre entidades

## Documentación API
Swagger disponible en:
http://localhost:8080/swagger-ui/index.html
