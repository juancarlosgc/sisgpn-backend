package com.policia.zona7.dto.asignaciones;

import java.util.Date;

public record DatosRespuestaVehiculoPersonaDto(
        Long idVehiculoPersona,
        Long idVehiculo,
        Long idPersona,
        Date fecha
) {
}
