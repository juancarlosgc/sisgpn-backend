package com.policia.zona7.dto.vehiculo;

import com.policia.zona7.model.TipoVehiculoEnum;

public record DatosActualizarVehiculoDto(Long idVehiculo,
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
                                        TipoVehiculoEnum tipoVehiculo){
}
