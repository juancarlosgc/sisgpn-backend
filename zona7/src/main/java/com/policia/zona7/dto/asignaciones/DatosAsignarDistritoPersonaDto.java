package com.policia.zona7.dto.asignaciones;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record DatosAsignarDistritoPersonaDto(
        Long idDistritoPersona,
        @NotNull
        Long idPersona,
        @NotNull
        Long idSubcircuito,

        Date fecha
) {
}
