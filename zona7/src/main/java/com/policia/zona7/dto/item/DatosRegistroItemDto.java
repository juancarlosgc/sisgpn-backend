package com.policia.zona7.dto.item;

import com.policia.zona7.model.MantenimientoModel;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroItemDto(

        @NotNull
        String nombreItem,

        @NotNull
        String codigoItem,

        String descripcionItem,
        Long idMantenimiento
) {
}
