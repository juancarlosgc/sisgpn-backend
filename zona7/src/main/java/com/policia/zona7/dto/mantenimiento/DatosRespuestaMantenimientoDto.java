package com.policia.zona7.dto.mantenimiento;

public record DatosRespuestaMantenimientoDto(
        Long idMantenimiento,
        String codigoMantenimiento,

        String nombreMantenimiento,

        Double costoMantenimiento
) {
}
