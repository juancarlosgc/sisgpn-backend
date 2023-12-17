package com.policia.zona7.dto.vehiculo;

import com.policia.zona7.model.TipoVehiculoEnum;
import jakarta.validation.constraints.NotBlank;

public record DatosRespuestaVehiculoDto(
        Long idVehiculo,

        String placa,

        String chasis,

        String marca,

        String modelo,

        String motor,

        Double kilometraje,

        Double cilindraje,

        Double capacidadCarga,

        Integer capacidadPasajeros,

        TipoVehiculoEnum tipoVehiculo,

        String observaciones
) {
}
