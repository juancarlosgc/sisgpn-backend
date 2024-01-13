package com.policia.zona7.controller;

import com.policia.zona7.dto.asignaciones.*;
import com.policia.zona7.dto.distrito.DatosActualizarDistritoDto;
import com.policia.zona7.dto.distrito.DatosRespuestaDistritoDto;
import com.policia.zona7.dto.subcircuito.DatosActualizarSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosListadoSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosRespuestaSubcircuitoDto;
import com.policia.zona7.model.DistritoModel;
import com.policia.zona7.model.DistritoPersonaModel;
import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.model.SubcircuitoModel;
import com.policia.zona7.repository.IDistritoPersonaRepository;
import com.policia.zona7.repository.IPersonaRepository;
import com.policia.zona7.repository.ISubcircuitoRepository;
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


    @PostMapping("/crear")
    @Transactional
    public ResponseEntity asinarDistritoPersona(@RequestBody @Valid DatosAsignarDistritoPersonaDto datosAsignarDistritoPersonaDto){
        service.guardar(datosAsignarDistritoPersonaDto);
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
       // distritoPersonaModel.desactivarSubcircuito();
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
