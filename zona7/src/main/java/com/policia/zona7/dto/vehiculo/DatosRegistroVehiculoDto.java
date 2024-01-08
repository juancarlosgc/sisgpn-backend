package com.policia.zona7.dto.vehiculo;

import com.policia.zona7.model.TipoVehiculoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroVehiculoDto(
        @NotBlank
        String placa,
        @NotBlank
        String chasis,
        @NotBlank
        String marca,
        @NotBlank
        String modelo,
        @NotBlank
        String motor,
        @NotNull
        Double kilometraje,
        @NotNull
        Double cilindraje,
        @NotNull
       // @NotBlank
        Double capacidadCarga,
        @NotNull
        Integer capacidadPasajeros,
        @NotNull
        TipoVehiculoEnum tipoVehiculo,

        String observaciones

) {

}
