package com.policia.zona7.dto.item;

public record DatosActualizarItemDto(
        Long idItem,
        String codigoItem,
        String nombreItem,
        String descripcionItem,
        Long idMantenimiento
) {
}
