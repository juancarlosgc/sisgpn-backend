package com.policia.zona7.dto.circuito;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCircuitoDto(
        @NotBlank
        String codigoCircuito,

        @NotBlank
        String nombreCircuito
) {
}
