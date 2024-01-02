package com.policia.zona7.dto.distrito;

public record DatosActualizarDistritoDto(
         Long idDistrito,
         String codigoDistrito,
         String nombreDistrito,
         String parroquia
) {
}
