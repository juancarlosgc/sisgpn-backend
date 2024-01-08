package com.policia.zona7.dto.circuito;

import com.policia.zona7.model.CircuitoModel;

public record DatosListadoCircuitoDto(
        Long idCircuito,
        String codigoCircuito,
        String nombreCircuito,
        Long idDistrito,
        String nombreDistrito

){
    public DatosListadoCircuitoDto(CircuitoModel circuitoModel){
        this(
                circuitoModel.getIdCircuito(),
                circuitoModel.getCodigoCircuito(),
                circuitoModel.getNombreCircuito(),
                circuitoModel.getDistrito().getIdDistrito(),
                circuitoModel.getDistrito().getNombreDistrito()
        );
    }



}
