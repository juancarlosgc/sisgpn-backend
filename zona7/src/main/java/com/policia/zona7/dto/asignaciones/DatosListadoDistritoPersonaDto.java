package com.policia.zona7.dto.asignaciones;

import com.policia.zona7.model.DistritoPersonaModel;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosListadoDistritoPersonaDto(
        Long idDistritoPersona,

        Long idPersona,

        Long idSubcircuito,
        String nombreSubcircuito,

        Date fecha,

        String apellidos,

        String nombres,

        String cedula
) {
    public DatosListadoDistritoPersonaDto(DistritoPersonaModel distritoPersonaModel){
        this(
                distritoPersonaModel.getIdDistritoPersona(),
                distritoPersonaModel.getPersona().getIdPersona(),
                distritoPersonaModel.getSubcircuito().getIdSubcircuito(),
                distritoPersonaModel.getSubcircuito().getNombreSubcircuito(),
                distritoPersonaModel.getFecha(),
                distritoPersonaModel.getPersona().getApellidos(),
                distritoPersonaModel.getPersona().getNombres(),
                distritoPersonaModel.getPersona().getCedula()
                //circuitoModel.getDistrito().getIdDistrito(),
                //circuitoModel.getDistrito().getNombreDistrito()
        );
    }
}
