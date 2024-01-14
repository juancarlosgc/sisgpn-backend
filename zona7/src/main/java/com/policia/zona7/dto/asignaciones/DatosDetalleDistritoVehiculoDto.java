package com.policia.zona7.dto.asignaciones;

import java.util.Date;

public record DatosDetalleDistritoVehiculoDto(
        Long idDistritoVehiculo,
        Long idVehiculo,
        Long idSubcircuito,

        Date fecha
) {
}
