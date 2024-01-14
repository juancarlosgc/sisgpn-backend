package com.policia.zona7.dto.asignaciones;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DatosActualizarVehiculoPersonaDto(
        Long idVehiculoPersona,
        Long idVehiculo,

        Long idPersona,
        Date fecha
) {
}
