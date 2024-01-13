package com.policia.zona7.dto.mantenimiento;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroMantenimientoDto(
        @NotNull
        String codigoMantenimiento,
        @NotNull
         String nombreMantenimiento,
        @NotNull
        Double costoMantenimiento

) {
}
