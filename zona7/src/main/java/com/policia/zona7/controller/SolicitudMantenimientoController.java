package com.policia.zona7.controller;


import com.policia.zona7.dto.asignaciones.DatosRespuestaDistritoVehiculoDto;
import com.policia.zona7.dto.solicitudMantenimiento.DatosDetalleSolicitudMantenimientoDto;
import com.policia.zona7.dto.solicitudMantenimiento.DatosRespuestaSolicitudMantenimientoDto;
import com.policia.zona7.dto.solicitudMantenimiento.DatosSolicitarMantenimientoDto;
import com.policia.zona7.model.DistritoVehiculoModel;
import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.model.SolicitudMantenimientoModel;
import com.policia.zona7.repository.*;
import com.policia.zona7.service.SolicitudMantenimientoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subcircuitos")
@CrossOrigin(origins = "http://localhost:4200")
public class SolicitudMantenimientoController {

    @Autowired
    private SolicitudMantenimientoService service;

    @Autowired
    private IPersonaRepository iPersonaRepository;

    @Autowired
    private ISubcircuitoRepository iSubcircuitoRepository;

    @Autowired
    private IVehiculoRepository iVehiculoRepository;

    @Autowired
    private ISolicitudMantenimientoRepository iSolicitudMantenimientoRepository;

    @PostMapping("/crear")
    @Transactional
    public ResponseEntity solicitarMantenimiento(@RequestBody @Valid DatosSolicitarMantenimientoDto datos){
        var idSubcircuito= iSubcircuitoRepository.findById(datos.idSubcircuito()).get();
        var idPersona= iPersonaRepository.findById(datos.idPersona()).get();
        var idVehiculo= iVehiculoRepository.findById(datos.idVehiculo()).get();
        var solicitudMantenimiento=new SolicitudMantenimientoModel(
                null,
                datos.fechaTentativa(),
                datos.horaTentativa(),
                datos.kilomentrajeActual(),
                datos.observaciones(),
                idPersona,
                idVehiculo,
                idSubcircuito
        );
        iSolicitudMantenimientoRepository.save(solicitudMantenimiento);
        return ResponseEntity.ok().build();

        /*service.solicitarMantenimiento(datos);
        return ResponseEntity.ok(new DatosDetalleSolicitudMantenimientoDto(null,null,null,null,null ,null,null,null));*/
    }

    @GetMapping("/ver/{idPersona}")
    @Transactional
    public ResponseEntity<?> listadoPersonaMantenimiento(@PathVariable Long idPersona){
        PersonaModel persona = iPersonaRepository.getReferenceById(idPersona);
        var datos= new DatosRespuestaSolicitudMantenimientoDto(
                persona.getIdPersona(),
                persona.getVehiculo().getIdVehiculo(),
                persona.getSubcircuito().getIdSubcircuito()
        );
        return ResponseEntity.ok(datos);
    }
}
