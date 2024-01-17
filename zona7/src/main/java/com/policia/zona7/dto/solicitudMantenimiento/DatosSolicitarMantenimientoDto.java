package com.policia.zona7.dto.solicitudMantenimiento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DatosSolicitarMantenimientoDto(
        Long idSolicitud,
        @NotNull
        @Future
        Date fechaTentativa,
        @NotNull
        Date horaTentativa,
        @NotNull
        Double kilomentrajeActual,
        String observaciones,
        @NotNull
        Long idPersona,
        @NotNull
        Long idVehiculo,
        @NotNull
        Long idSubcircuito
) {
}
