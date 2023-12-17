package com.policia.zona7.dto.persona;

import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.model.RangoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;

public record DatosListadoPersonaDto(
        String cedula,

        String apellidos,

        String nombres,

        String fechaNacimiento,

        String tipoSangre,

        String ciudadNacimiento,

        String telefono,

        RangoEnum rango
        ) {
    public DatosListadoPersonaDto(PersonaModel personaModel){
        this(personaModel.getCedula(),
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
