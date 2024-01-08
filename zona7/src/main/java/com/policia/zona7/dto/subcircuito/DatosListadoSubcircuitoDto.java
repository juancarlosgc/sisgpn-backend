package com.policia.zona7.dto.subcircuito;

import com.policia.zona7.model.SubcircuitoModel;

public record DatosListadoSubcircuitoDto(
        Long idSubcircuito,
        String codigoSubcircuito,
        String nombreSubcircuito
) {
    public DatosListadoSubcircuitoDto(SubcircuitoModel subcircuitoModel){
        this(
                subcircuitoModel.getIdSubcircuito(),
                subcircuitoModel.getCodigoSubcircuito(),
                subcircuitoModel.getNombreSubcircuito()
        );
    }
}
