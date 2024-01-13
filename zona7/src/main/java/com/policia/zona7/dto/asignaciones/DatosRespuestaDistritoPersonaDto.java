package com.policia.zona7.dto.asignaciones;

import java.util.Date;

public record DatosRespuestaDistritoPersonaDto(
        Long idDistritoPersona,
        Long idPersona,
        Long idSubcircuito,
        //String nombreSubcircuito,
        Date fecha
        //String apellidos,
        //String nombres,
        //String cedula
) {
}
