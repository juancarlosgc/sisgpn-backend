package com.policia.zona7.dto.circuito;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroCircuitoDto(
        Long idCircuito,
        @NotNull
        String codigoCircuito,
        @NotNull
        String nombreCircuito,
        @NotNull
        Long idDistrito
) {
}
