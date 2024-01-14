package com.policia.zona7.dto.asignaciones;

import com.policia.zona7.model.VehiculoModel;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DatosAsignarVehiculoPersonaDto(
        Long idVehiculoPersona,
        @NotNull
        Long idVehiculo,
        @NotNull
        Long idPersona,
        Date fecha


) {
}
