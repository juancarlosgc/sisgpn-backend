package com.policia.zona7.dto.asignaciones;

import java.util.Date;

public record DatosActualizarDistritoVehiculoDto(
        Long idDistritoVehiculo,
        Long idVehiculo,
        Long idSubcircuito,

        Date fecha
) {
}
