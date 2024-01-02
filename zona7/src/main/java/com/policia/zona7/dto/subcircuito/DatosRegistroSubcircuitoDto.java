package com.policia.zona7.dto.subcircuito;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroSubcircuitoDto(
        @NotBlank
        String codigoSubcircuito,

        @NotBlank
        String nombreSubcircuito
) {
}
