# Tareas priorizadas tras revisión rápida del código

## 1) Corregir error tipográfico
**Tipo:** Typo / nomenclatura

**Problema detectado**
El nombre de la clase de configuración OpenAPI está escrito como `AbrirApiConfig`, lo que parece un error tipográfico y reduce la claridad del propósito técnico del archivo.

**Tarea propuesta**
Renombrar `AbrirApiConfig` a `OpenApiConfig` y ajustar referencias/imports si aplica.

**Criterios de aceptación**
- La clase se llama `OpenApiConfig`.
- El proyecto compila sin errores de importación o de escaneo de componentes.

---

## 2) Corregir una falla funcional (NPE potencial)
**Tipo:** Bug

**Problema detectado**
En `PedidosServicio`, el repositorio `platosxPedidoRepositorio` no está inyectado y se utiliza en `saveall(...)`, lo que puede producir un `NullPointerException` en runtime cuando se intenta guardar detalles del pedido.

**Tarea propuesta**
Inyectar correctamente `PlatosxPedidoRepositorio` (idealmente con inyección por constructor) y agregar prueba unitaria/integración que cubra `saveall(...)`.

**Criterios de aceptación**
- `saveall(...)` no lanza `NullPointerException` por dependencias nulas.
- Existe prueba automatizada que falle antes del fix y pase después del fix.

---

## 3) Corregir discrepancia entre documentación/comentarios y comportamiento real
**Tipo:** Documentación técnica

**Problema detectado**
En `PedidosRest.savePedido(...)`, la anotación OpenAPI documenta `responseCode = "200"`, pero el método devuelve `ResponseEntity.created(...)` (HTTP 201).

**Tarea propuesta**
Actualizar la documentación OpenAPI para reflejar `201 Created` como respuesta exitosa de creación.

**Criterios de aceptación**
- Swagger/OpenAPI muestra `201` como código de éxito del endpoint de creación.
- Se mantiene `400` para errores de validación/datos inválidos.

---

## 4) Mejorar una prueba automatizada
**Tipo:** Testing

**Problema detectado**
La suite actual solo tiene `contextLoads()`, lo que valida arranque de contexto pero no comportamiento de endpoints/servicios clave.

**Tarea propuesta**
Agregar pruebas de controlador para `POST /pedidos/` y `GET /pedidos/{id}` (casos feliz y error), verificando códigos HTTP y estructura básica de respuesta.

**Criterios de aceptación**
- Existen pruebas automatizadas para al menos dos endpoints de `PedidosRest`.
- Se validan códigos de estado esperados (`201` en creación exitosa, `400` en payload inválido o `404` en no encontrado según diseño).
