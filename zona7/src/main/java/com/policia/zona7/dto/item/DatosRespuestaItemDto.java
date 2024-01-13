package com.policia.zona7.dto.item;

public record DatosRespuestaItemDto(
        Long idItem,
        String codigoItem,
        String nombreItem,
        String descripcionItem,
        Long idMantenimiento
) {
}
