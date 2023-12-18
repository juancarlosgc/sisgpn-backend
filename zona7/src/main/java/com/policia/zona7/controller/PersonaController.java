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

@RestController
@RequestMapping("personas")
public class PersonaController {

    @Autowired
    private IPersonaRepository iPersonaRepository;

    @Autowired
    private PersonaService personaService;

    @PostMapping
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


    @GetMapping
    public ResponseEntity<Page<DatosListadoPersonaDto>> listadoMedicos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iPersonaRepository.findByEstaActivoTrue(paginacion).map(DatosListadoPersonaDto::new));
    }

    @PutMapping
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

    @DeleteMapping("/{idPersona}")
    @Transactional
    public ResponseEntity eliminarPersona(@PathVariable Long idPersona){
        PersonaModel persona = iPersonaRepository.getReferenceById(idPersona);

        persona.desactivarPersona();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idPersona}")
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

   /* @GetMapping("/apellido/{apellido}")
    public ResponseEntity<DatosRespuestaPersonaDto> buscarPorApellido(@PathVariable ("apellido") String apellido){
            if (personaService.)
    }*/
}
