package com.policia.zona7.dto.circuito;

public record DatosDetalleCircuitoDto(
        Long idCircuito,
        String codigoCircuito,
        String nombreCircuito,
        Long idDistrito
) {
}
