package com.policia.zona7.dto.persona;

import com.policia.zona7.model.RangoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record DatosRespuestaPersonaDto(
        Long idPersona,
        String cedula,
        String apellidos,

        String nombres,

        Date fechaNacimiento,

        String tipoSangre,

        String ciudadNacimiento,

        String telefono,

        RangoEnum rango)
{
}
