package com.policia.zona7.dto.subcircuito;

public record  DatosDetalleSubcircuitoDto(
        Long idSubcircuito,
        String codigoSubcircuito,
        String nombreSubcircuito,
        Long idCircuito
) {
}
