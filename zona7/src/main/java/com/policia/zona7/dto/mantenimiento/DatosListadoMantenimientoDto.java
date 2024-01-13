package com.policia.zona7.dto.mantenimiento;

import com.policia.zona7.model.MantenimientoModel;

public record DatosListadoMantenimientoDto(
        Long idMantenimiento,
        String codigoMantenimiento,

        String nombreMantenimiento,

        Double costoMantenimiento
) {

    public DatosListadoMantenimientoDto(MantenimientoModel mantenimientoModel){
        this(
          mantenimientoModel.getIdMantenimiento(),
          mantenimientoModel.getCodigoMantenimiento(),
          mantenimientoModel.getNombreMantenimiento(),
          mantenimientoModel.getCostoMantenimiento()
        );
    }
}
