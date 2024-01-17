package com.policia.zona7.service;

import com.policia.zona7.dto.solicitudMantenimiento.DatosSolicitarMantenimientoDto;
import com.policia.zona7.infra.ValidacionIntegridad;
import com.policia.zona7.model.SolicitudMantenimientoModel;
import com.policia.zona7.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudMantenimientoService {

    @Autowired
    private ISolicitudMantenimientoRepository iSolicitudMantenimientoRepository;
    @Autowired
    private IPersonaRepository iPersonaRepository;
    @Autowired
    private IVehiculoRepository iVehiculoRepository;
    @Autowired
    private ISubcircuitoRepository iSubcircuitoRepository;
    @Autowired
    private IVehiculoPersonaRepository iVehiculoPersonaRepository;

    public void solicitarMantenimiento(DatosSolicitarMantenimientoDto datos){



        var personaVehiculo= iVehiculoPersonaRepository.findByIdPersona(datos.idPersona());

        if (personaVehiculo!=null){
            System.out.println("Persona asignada a vehiculo");
        }

     /*   if (iPersonaRepository.findById(datos.idPersona()).isPresent()){
            throw new ValidacionIntegridad("Persona no econtrada");
        }*/


        var persona= iPersonaRepository.findById(datos.idPersona()).get();
        var vehiculo=iVehiculoRepository.findById(datos.idVehiculo()).get();
        var subcircuito=iSubcircuitoRepository.findById(datos.idSubcircuito()).get();

            var mantenimiento= new SolicitudMantenimientoModel(
                    null,
                    datos.fechaTentativa(),
                    datos.horaTentativa(),
                    datos.kilomentrajeActual(),
                    datos.observaciones(),
                    persona,
                    vehiculo,
                    subcircuito
                    );

            iSolicitudMantenimientoRepository.save(mantenimiento);
    }
}
