package com.policia.zona7.dto.distrito;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroDistritoDto(
         @NotBlank
         String codigoDistrito,
         @NotBlank
         String nombreDistrito,
         @NotBlank
         String parroquia
) {

}
