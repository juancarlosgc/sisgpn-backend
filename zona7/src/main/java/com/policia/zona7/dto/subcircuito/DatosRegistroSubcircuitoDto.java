package com.policia.zona7.dto.subcircuito;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroSubcircuitoDto(

        Long idSubcircuito,
        @NotBlank
        String codigoSubcircuito,

        @NotBlank
        String nombreSubcircuito,

        Long idCircuito
) {
}
