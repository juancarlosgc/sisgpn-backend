package com.policia.zona7.dto.asignaciones;

import com.policia.zona7.model.DistritoPersonaModel;
import com.policia.zona7.model.DistritoVehiculoModel;

import java.util.Date;

public record DatosListadoDistritoVehiculoDto(
        Long idDistritoVehiculo,
        Long idVehiculo,
        Long idSubcircuito,

        Date fecha,
        String nombreSubcircuito,
        String placa,
        String marca

) {
    public DatosListadoDistritoVehiculoDto(DistritoVehiculoModel distritoVehiculoModel){
        this(
                distritoVehiculoModel.getIdDistritoVehiculo(),
                distritoVehiculoModel.getSubcircuito().getIdSubcircuito(),
                distritoVehiculoModel.getVehiculo().getIdVehiculo(),
                distritoVehiculoModel.getFecha(),
                distritoVehiculoModel.getSubcircuito().getNombreSubcircuito(),
                distritoVehiculoModel.getVehiculo().getPlaca(),
                distritoVehiculoModel.getVehiculo().getMarca()

        );
    }
}
