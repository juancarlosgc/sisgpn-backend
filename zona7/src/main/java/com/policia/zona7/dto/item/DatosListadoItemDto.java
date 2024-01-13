package com.policia.zona7.dto.item;

import com.policia.zona7.model.ItemMantenimientoModel;
import com.policia.zona7.model.SubcircuitoModel;

public record DatosListadoItemDto(
        Long idItem,
        String codigoItem,
        String nombreItem,
        String descripcionItem,
        Long idMantenimiento,
        String nombreMantenimiento
) {

    public DatosListadoItemDto(ItemMantenimientoModel itemMantenimientoModel){
        this(
                itemMantenimientoModel.getIdItem(),
                itemMantenimientoModel.getCodigoItem(),
                itemMantenimientoModel.getNombreItem(),
                itemMantenimientoModel.getDescripcionItem(),
                itemMantenimientoModel.getMantenimiento().getIdMantenimiento(),
                itemMantenimientoModel.getMantenimiento().getNombreMantenimiento()
        );
    }
}
