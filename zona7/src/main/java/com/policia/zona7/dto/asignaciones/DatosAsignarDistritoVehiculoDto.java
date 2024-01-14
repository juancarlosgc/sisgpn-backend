package com.policia.zona7.dto.asignaciones;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DatosAsignarDistritoVehiculoDto(
        Long idDistritoVehiculo,
        @NotNull
        Long idVehiculo,
        @NotNull
        Long idSubcircuito,

        Date fecha
) {
}
