package com.policia.zona7.dto.asignaciones;

import com.policia.zona7.model.VehiculoPersonaModel;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DatosListadoVehiculoPersonaDto(
        Long idVehiculoPersona,
        Long idVehiculo,
        Long idPersona,
        Date fecha,
        String marca,
        String modelo,
        String placa,
        String apellidos,
        String nombres,
        String cedula

) {

    public DatosListadoVehiculoPersonaDto(VehiculoPersonaModel vehiculoPersonaModel){
        this(
                vehiculoPersonaModel.getIdVehiculoPersona(),
                vehiculoPersonaModel.getVehiculo().getIdVehiculo(),
                vehiculoPersonaModel.getPersona().getIdPersona(),
                vehiculoPersonaModel.getFecha(),
                vehiculoPersonaModel.getVehiculo().getMarca(),
                vehiculoPersonaModel.getVehiculo().getModelo(),
                vehiculoPersonaModel.getVehiculo().getPlaca(),
                vehiculoPersonaModel.getPersona().getApellidos(),
                vehiculoPersonaModel.getPersona().getNombres(),
                vehiculoPersonaModel.getPersona().getCedula()
        );
    }
}
