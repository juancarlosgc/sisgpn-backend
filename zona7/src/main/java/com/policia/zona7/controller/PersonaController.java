package com.policia.zona7.controller;

import com.policia.zona7.dto.persona.DatosActualizarPersonaDto;
import com.policia.zona7.dto.persona.DatosListadoPersonaDto;
import com.policia.zona7.dto.persona.DatosRegistroPersonaDto;
import com.policia.zona7.dto.persona.DatosRespuestaPersonaDto;
import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.repository.IPersonaRepository;
import com.policia.zona7.service.PersonaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("personas")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

    @Autowired
    private IPersonaRepository iPersonaRepository;


    @GetMapping("/vertodos")
    public List<PersonaModel> list(){
        return iPersonaRepository.findAll();
    }

    @PostMapping("/crearpersona")
    public ResponseEntity<DatosRespuestaPersonaDto> resgistrarPersona(@RequestBody @Valid DatosRegistroPersonaDto datosRegistroPersonaDto,
                                            UriComponentsBuilder uriComponentsBuilder){
       PersonaModel persona = iPersonaRepository.save(new PersonaModel(datosRegistroPersonaDto));
        DatosRespuestaPersonaDto datosRespuestaPersonaDto = new DatosRespuestaPersonaDto(
                persona.getIdPersona(),
                persona.getCedula(),
                persona.getApellidos(),
                persona.getNombres(),
                persona.getFechaNacimiento(),
                persona.getTipoSangre(),
                persona.getCiudadNacimiento(),
                persona.getTelefono(),
                persona.getRango()
        );
        URI url = uriComponentsBuilder.path("/personas/{idPersona}").buildAndExpand(persona.getIdPersona()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPersonaDto);
    }


   @GetMapping("/vertodo")
    public ResponseEntity<Page<DatosListadoPersonaDto>> listadoPersona(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(iPersonaRepository.findByEstaActivoTrue(paginacion).map(DatosListadoPersonaDto::new));
    }

    @PutMapping("/editar")
    @Transactional
    public ResponseEntity actualizarPersona(@RequestBody @Valid DatosActualizarPersonaDto datosActualizarPersonaDto){
        PersonaModel persona  =iPersonaRepository.getReferenceById(datosActualizarPersonaDto.idPersona());
        persona.actualizarDatosPersona(datosActualizarPersonaDto);
        return ResponseEntity.ok(new DatosRespuestaPersonaDto(
                persona.getIdPersona(),
                persona.getCedula(),
                persona.getApellidos(),
                persona.getNombres(),
                persona.getFechaNacimiento(),
                persona.getTipoSangre(),
                persona.getCiudadNacimiento(),
                persona.getTelefono(),
                persona.getRango()
                ));
    }

    @DeleteMapping("/eliminar/{idPersona}")
    @Transactional
    public ResponseEntity eliminarPersona(@PathVariable Long idPersona){
        PersonaModel persona = iPersonaRepository.getReferenceById(idPersona);

        persona.desactivarPersona();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ver/{idPersona}")
    @Transactional
    public ResponseEntity<DatosRespuestaPersonaDto> listadoPersonaId(@PathVariable Long idPersona){
        PersonaModel persona = iPersonaRepository.getReferenceById(idPersona);
        var datosPersona = new DatosRespuestaPersonaDto( persona.getIdPersona(),
                persona.getCedula(),
                persona.getApellidos(),
                persona.getNombres(),
                persona.getFechaNacimiento(),
                persona.getTipoSangre(),
                persona.getCiudadNacimiento(),
                persona.getTelefono(),
                persona.getRango());
        return ResponseEntity.ok(datosPersona);
    }
}
