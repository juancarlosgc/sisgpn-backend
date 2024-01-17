package com.policia.zona7.controller;

import com.policia.zona7.dto.asignaciones.*;
import com.policia.zona7.dto.distrito.DatosActualizarDistritoDto;
import com.policia.zona7.dto.distrito.DatosRespuestaDistritoDto;
import com.policia.zona7.dto.subcircuito.DatosActualizarSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosListadoSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosRespuestaSubcircuitoDto;
import com.policia.zona7.model.*;
import com.policia.zona7.repository.IDistritoPersonaRepository;
import com.policia.zona7.repository.IPersonaRepository;
import com.policia.zona7.repository.ISubcircuitoRepository;
import com.policia.zona7.repository.IVehiculoRepository;
import com.policia.zona7.service.AsignarDistritoPersonaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("distritospersonas")
@CrossOrigin(origins = "http://localhost:4200")
public class AsignaDistritoAPersonaController {

    @Autowired
    private AsignarDistritoPersonaService service;

    @Autowired
    private IDistritoPersonaRepository iDistritoPersonaRepository;

    @Autowired
    private IPersonaRepository iPersonaRepository;

    @Autowired
    private ISubcircuitoRepository iSubcircuitoRepository;

    @Autowired
    private IVehiculoRepository iVehiculoRepository;


    @PostMapping("/crear")
    @Transactional
    public ResponseEntity asinarDistritoPersona(@RequestBody @Valid DatosAsignarDistritoPersonaDto datos){
        var persona= iPersonaRepository.findById(datos.idPersona()).get();
        var idSubcircuito= iSubcircuitoRepository.findById(datos.idSubcircuito()).get();

        VehiculoModel vehiculo= persona.getVehiculo();

        service.guardar(datos);

     /*   var personaDistrito=new PersonaModel(datos.idPersona(),persona.getCedula(),persona.getApellidos(),persona.getNombres(),persona.getFechaNacimiento(),persona.getTipoSangre(),persona.getCiudadNacimiento(),persona.getTelefono(),persona.getRango(),true,vehiculo,idSubcircuito);
        iPersonaRepository.save(personaDistrito);*/

        return ResponseEntity.ok(new DatosDetalleAsignarDistritoPersona(null,null,null,null));
    }

    @GetMapping("/vertodo")
    public ResponseEntity<Page<DatosListadoDistritoPersonaDto>> listadoSubcircuito(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iDistritoPersonaRepository.findAll(paginacion).map(DatosListadoDistritoPersonaDto::new));
    }

    @GetMapping("/ver/{idDistritopersona}")
    @Transactional
    public ResponseEntity<?> listadoDistritoPersonaId(@PathVariable Long idDistritopersona){
        DistritoPersonaModel distritopersona = iDistritoPersonaRepository.getReferenceById(idDistritopersona);
        var datosRespuestaDistritoPersonaDto= new DatosRespuestaDistritoPersonaDto(
                distritopersona.getIdDistritoPersona(),
                distritopersona.getPersona().getIdPersona(),
                distritopersona.getSubcircuito().getIdSubcircuito(),
                //distritopersona.getSubcircuito().getNombreSubcircuito(),
                distritopersona.getFecha());

               // distritopersona.getPersona().getApellidos(),
                //distritopersona.getPersona().getNombres(),
                //distritopersona.getPersona().getCedula());
        return ResponseEntity.ok(datosRespuestaDistritoPersonaDto);
    }


    @PutMapping("/editar")
    @Transactional
    public void actualizarDistritoPersona(@RequestBody @Valid DatosActualizarDistritoPersonaDto datos){
        service.editar(datos);
    }

    @DeleteMapping("/eliminar/{idDistritoPersona}")
    @Transactional
    public ResponseEntity eliminarSubcircuito(@PathVariable Long idDistritoPersona){
        DistritoPersonaModel distritoPersonaModel = iDistritoPersonaRepository.getReferenceById(idDistritoPersona);

        ///////
        var idPersona= distritoPersonaModel.getPersona().getIdPersona();
        var persona= iPersonaRepository.findById(distritoPersonaModel.getPersona().getIdPersona()).get();
      //  var personaDistrito=new PersonaModel(idPersona,persona.getCedula(),persona.getApellidos(),persona.getNombres(),persona.getFechaNacimiento(),persona.getTipoSangre(),persona.getCiudadNacimiento(),persona.getTelefono(),persona.getRango(),true);
      //  iPersonaRepository.save(personaDistrito);
        ///////

        iDistritoPersonaRepository.delete(distritoPersonaModel);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listarpersonas")
    public List<PersonaModel> listpersonas(){
        return iPersonaRepository.findAll();
    }

    @GetMapping("/listarsubcircuitos")
    public List<SubcircuitoModel>  listsubcircuitos (){
        return iSubcircuitoRepository.findAll();
    }
}
