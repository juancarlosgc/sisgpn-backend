package com.policia.zona7.dto.asignaciones;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record DatosDetalleAsignarDistritoPersona(
        Long idDistritoPersona,

        Long idPersona,

        Long idSubcircuito,

        Date fecha
) {
}
