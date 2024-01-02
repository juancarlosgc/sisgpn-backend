package com.policia.zona7.dto.circuito;

import com.policia.zona7.model.CircuitoModel;
import com.policia.zona7.model.DistritoModel;

public record DatosListadoCircuitoDto(
        Long idCircuito,
        String codigoCircuito,
        String nombreCircuito
) {
    public DatosListadoCircuitoDto(CircuitoModel circuitoModel){
        this(
                circuitoModel.getIdCircuito(),
                circuitoModel.getCodigoCircuito(),
                circuitoModel.getNombreCircuito()
        );
    }
}
