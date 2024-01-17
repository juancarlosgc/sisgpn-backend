package com.policia.zona7.controller;

import com.policia.zona7.dto.asignaciones.*;
import com.policia.zona7.model.*;
import com.policia.zona7.repository.IPersonaRepository;
import com.policia.zona7.repository.IVehiculoPersonaRepository;
import com.policia.zona7.repository.IVehiculoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("vehiculospersonas")
@CrossOrigin(origins = "http://localhost:4200")
public class AsignaVehiculoPersonaController {

    @Autowired
    private IVehiculoPersonaRepository iVehiculoPersonaRepository;

    @Autowired
    private IPersonaRepository iPersonaRepository;

    @Autowired
    private IVehiculoRepository iVehiculoRepository;

    @PostMapping("/crear")
    @Transactional
    public ResponseEntity asignarVehiculoPersona(@RequestBody @Valid DatosAsignarVehiculoPersonaDto datos){
        var persona= iPersonaRepository.findById(datos.idPersona()).get();
        var idVehiculo= iVehiculoRepository.findById(datos.idVehiculo()).get();

        var vehiculoPersona=new VehiculoPersonaModel(
                    null,
                    idVehiculo,
                    persona,
                    datos.fecha()
            );
        iVehiculoPersonaRepository.save(vehiculoPersona);

        Optional<PersonaModel> idSubcircuito = iPersonaRepository.findById(datos.idPersona());

/*
        //Actualiza tabla persona con el id de vehiculo asignado
      var personaVehiculo=new PersonaModel(datos.idPersona(),persona.getCedula(),persona.getApellidos(),persona.getNombres(),persona.getFechaNacimiento(),persona.getTipoSangre(),persona.getCiudadNacimiento(),persona.getTelefono(),persona.getRango(),true,idVehiculo,idSubcircuito.get().getSubcircuito().getIdSubcircuito());
       iPersonaRepository.save(personaVehiculo);*/

        return ResponseEntity.ok().build();
    }

    @GetMapping("/vertodo")
    public ResponseEntity<Page<DatosListadoVehiculoPersonaDto>> listadoVehiculoPersona(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iVehiculoPersonaRepository.findAll(paginacion).map(DatosListadoVehiculoPersonaDto::new));
    }

    @PutMapping("/editar")
    @Transactional
    public ResponseEntity actualizarVehiculoPersona(@RequestBody @Valid DatosActualizarVehiculoPersonaDto datos){
        VehiculoPersonaModel vehiculoPersona=iVehiculoPersonaRepository.getReferenceById(datos.idVehiculoPersona());
        var persona=iPersonaRepository.getReferenceById(datos.idPersona());
        var vehiculo= iVehiculoRepository.getReferenceById(datos.idVehiculo());
        var vehiculoPersonaEditado=new VehiculoPersonaModel(vehiculoPersona.getIdVehiculoPersona(),vehiculo,persona,vehiculoPersona.getFecha());
        iVehiculoPersonaRepository.save(vehiculoPersonaEditado);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ver/{idVehiculoPersona}")
    @Transactional
    public ResponseEntity<?> listadoVehiculoPersonaId(@PathVariable Long idVehiculoPersona){
        VehiculoPersonaModel vehiculoPersona = iVehiculoPersonaRepository.getReferenceById(idVehiculoPersona);
        var datosRespuestaVehiculoPersonaDto= new DatosRespuestaVehiculoPersonaDto(
                vehiculoPersona.getIdVehiculoPersona(),
                vehiculoPersona.getVehiculo().getIdVehiculo(),
                vehiculoPersona.getPersona().getIdPersona(),
                vehiculoPersona.getFecha()
        );
        return ResponseEntity.ok(datosRespuestaVehiculoPersonaDto);
    }

    @DeleteMapping("/eliminar/{idVehiculoPersona}")
    @Transactional
    public ResponseEntity eliminarSubcircuito(@PathVariable @RequestBody Long idVehiculoPersona,  DatosAsignarVehiculoPersonaDto datos){

        VehiculoPersonaModel vehiculoPersonaModel = iVehiculoPersonaRepository.getReferenceById(idVehiculoPersona);

        var idPersona= vehiculoPersonaModel.getPersona().getIdPersona();
        var persona= iPersonaRepository.findById(vehiculoPersonaModel.getPersona().getIdPersona()).get();
      //  var personaVehiculo=new PersonaModel(idPersona,persona.getCedula(),persona.getApellidos(),persona.getNombres(),persona.getFechaNacimiento(),persona.getTipoSangre(),persona.getCiudadNacimiento(),persona.getTelefono(),persona.getRango(),true);
        //iPersonaRepository.save(personaVehiculo);

        iVehiculoPersonaRepository.delete(vehiculoPersonaModel);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listarvehiculos")
    public List<VehiculoModel> listvehiculos(){
        return iVehiculoRepository.findAll();
    }

    @GetMapping("/listarpersonas")
    public List<PersonaModel>  listpersonas (){
        return iPersonaRepository.findAll();
    }
}
