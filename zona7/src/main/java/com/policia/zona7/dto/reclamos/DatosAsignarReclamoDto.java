package com.policia.zona7.dto.reclamos;

import com.policia.zona7.model.TipoIncidenteEnum;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DatosAsignarReclamoDto(
        Long idReclamo,
        @NotNull
        TipoIncidenteEnum tipoIncidente,
        String detalle,
        @NotNull
        String contacto,
        @NotNull
        String apellidos,
        @NotNull
        String nombres,

        Date fecha,

        Long idSubcircuito,
        Long idCircuito
) {
}
