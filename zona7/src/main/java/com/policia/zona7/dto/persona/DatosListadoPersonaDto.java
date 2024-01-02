package com.policia.zona7.dto.persona;

import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.model.RangoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;

import java.time.LocalDate;
import java.util.Date;

public record DatosListadoPersonaDto(
        Long idPersona,
        String cedula,

        String apellidos,

        String nombres,

        Date fechaNacimiento,

        String tipoSangre,

        String ciudadNacimiento,

        String telefono,

        RangoEnum rango
        ) {
    public DatosListadoPersonaDto(PersonaModel personaModel){
        this(personaModel.getIdPersona(),
                personaModel.getCedula(),
                personaModel.getApellidos(),
                personaModel.getNombres(),
                personaModel.getFechaNacimiento(),
                personaModel.getTipoSangre(),
                personaModel.getCiudadNacimiento(),
                personaModel.getTelefono(),
                personaModel.getRango()
        );
    }
}
