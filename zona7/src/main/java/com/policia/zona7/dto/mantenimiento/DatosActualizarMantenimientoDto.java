package com.policia.zona7.dto.mantenimiento;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarMantenimientoDto(
        Long idMantenimiento,

        String codigoMantenimiento,

        String nombreMantenimiento,

        Double costoMantenimiento
) {
}
