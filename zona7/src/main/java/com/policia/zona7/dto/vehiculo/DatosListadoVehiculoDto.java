package com.policia.zona7.dto.vehiculo;

import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.model.TipoVehiculoEnum;
import com.policia.zona7.model.VehiculoModel;

public record DatosListadoVehiculoDto(
        String placa,
        String chasis,
        String marca,
        String modelo,
        String motor,
        Double kilometraje,
        Double cilindraje,
        Double capacidadCarga,
        Integer capacidadPasajeros,
        String observaciones,
        TipoVehiculoEnum tipoVehiculo

) {

    public DatosListadoVehiculoDto(VehiculoModel vehiculoModel){
        this(vehiculoModel.getPlaca(),
                vehiculoModel.getChasis(),
                vehiculoModel.getMarca(),
                vehiculoModel.getModelo(),
                vehiculoModel.getMotor(),
                vehiculoModel.getKilometraje(),
                vehiculoModel.getCilindraje(),
                vehiculoModel.getCapacidadCarga(),
                vehiculoModel.getCantidadPasajeros(),
                vehiculoModel.getObservaciones(),
                vehiculoModel.getTipoVehiculo()
        );
}
}
