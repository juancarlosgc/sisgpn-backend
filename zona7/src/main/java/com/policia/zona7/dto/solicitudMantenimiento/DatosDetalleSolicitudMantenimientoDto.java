package com.policia.zona7.dto.solicitudMantenimiento;

import java.util.Date;

public record DatosDetalleSolicitudMantenimientoDto(
        Long idSolicitud,
        Date fechaTentativa,
        Date horaTentativa,
        Double kilomentrajeActual,
        String observaciones,
        Long idPersona,
        Long idVehiculo,
        Long id_Subcircuito
) {
}
