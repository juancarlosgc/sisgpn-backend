package com.policia.zona7.dto.asignaciones;

import java.util.Date;

public record DatosRespuestaDistritoVehiculoDto(
        Long idDistritoVehiculo,
        Long idVehiculo,
        Long idSubcircuito,
        Date fecha
) {
}
