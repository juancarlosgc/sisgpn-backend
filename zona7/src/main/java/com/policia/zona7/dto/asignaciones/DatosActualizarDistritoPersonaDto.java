package com.policia.zona7.dto.asignaciones;

import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.model.SubcircuitoModel;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DatosActualizarDistritoPersonaDto(
        Long idDistritoPersona,

        Long idPersona,

        Long idSubcircuito,

        Date fecha
) {
}
