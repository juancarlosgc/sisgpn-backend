package com.policia.zona7.dto.distrito;

import com.policia.zona7.model.DistritoModel;
import jakarta.validation.constraints.NotBlank;

public record DatosListadoDistritoDto(

        Long idDistrito,
        String codigoDistrito,
        String nombreDistrito,
        String parroquia
) {

    public DatosListadoDistritoDto(DistritoModel distritoModel){
        this(
                distritoModel.getIdDistrito(),
                distritoModel.getCodigoDistrito(),
                distritoModel.getNombreDistrito(),
                distritoModel.getParroquia()
            );
    }
}
