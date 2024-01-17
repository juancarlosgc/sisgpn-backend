package com.policia.zona7.dto.reclamos;

import com.policia.zona7.model.ReclamosModel;
import com.policia.zona7.model.TipoIncidenteEnum;
import com.policia.zona7.model.VehiculoPersonaModel;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DatosListadoReclamoDto(
        Long idReclamo,

        TipoIncidenteEnum tipoIncidente,
        String detalle,

        String contacto,

        String apellidos,

        String nombres,

        Date fecha,

        Long idSubcircuito,
         String nombreSubcircuito,

         Long idCircuito,
         String nombreCircuito
) {

    public DatosListadoReclamoDto(ReclamosModel reclamosModel){
        this(
                reclamosModel.getIdReclamo(),
                reclamosModel.getTipoIncidente(),
                reclamosModel.getDetalle(),
                reclamosModel.getContacto(),
                reclamosModel.getApellidos(),
                reclamosModel.getNombres(),
                reclamosModel.getFecha(),
                reclamosModel.getSubcircuitos().getIdSubcircuito(),
                reclamosModel.getSubcircuitos().getNombreSubcircuito(),
                reclamosModel.getCircuitos().getIdCircuito(),
                reclamosModel.getCircuitos().getNombreCircuito()

        );
    }
}
