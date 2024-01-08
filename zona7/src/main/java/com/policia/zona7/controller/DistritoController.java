package com.policia.zona7.controller;

import com.policia.zona7.dto.distrito.DatosActualizarDistritoDto;
import com.policia.zona7.dto.distrito.DatosListadoDistritoDto;
import com.policia.zona7.dto.distrito.DatosRegistroDistritoDto;
import com.policia.zona7.dto.distrito.DatosRespuestaDistritoDto;
import com.policia.zona7.model.DistritoModel;
import com.policia.zona7.repository.IDistritoRepository;
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
@RequestMapping("distritos")
@CrossOrigin(origins = "http://localhost:4200")
public class DistritoController {

    @Autowired
    private IDistritoRepository iDistritoRepository;

    @PostMapping("/creardistrito")
    public ResponseEntity<DatosRespuestaDistritoDto> resgistrarDistrito(@RequestBody @Valid DatosRegistroDistritoDto datosRegistroDistritoDto,
                                                                        UriComponentsBuilder uriComponentsBuilder){
        DistritoModel distrito = iDistritoRepository.save(new DistritoModel(datosRegistroDistritoDto));
        DatosRespuestaDistritoDto datosRespuestaDistritoDto = new DatosRespuestaDistritoDto(
                distrito.getIdDistrito(),
                distrito.getCodigoDistrito(),
                distrito.getNombreDistrito(),
                distrito.getParroquia()
        );
        URI url = uriComponentsBuilder.path("/distritos/{idDistrito}").buildAndExpand(distrito.getIdDistrito()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaDistritoDto);
    }

    @GetMapping("/vertodo")
    public ResponseEntity<Page<DatosListadoDistritoDto>> listadoDistrito(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iDistritoRepository.findByEstaActivoTrue(paginacion).map(DatosListadoDistritoDto::new));
    }

    @PutMapping("/editar")
    @Transactional
    public ResponseEntity actualizarDistrito(@RequestBody @Valid DatosActualizarDistritoDto datosActualizarDistritoDto){
        DistritoModel distrito  =iDistritoRepository.getReferenceById(datosActualizarDistritoDto.idDistrito());
        distrito.actualizarDatosDistrito(datosActualizarDistritoDto);
        return ResponseEntity.ok(new DatosRespuestaDistritoDto(
                distrito.getIdDistrito(),
                distrito.getCodigoDistrito(),
                distrito.getNombreDistrito(),
                distrito.getParroquia()

        ));
    }

    @DeleteMapping("/eliminar/{idDistrito}")
    @Transactional
    public ResponseEntity eliminarDistrito(@PathVariable Long idDistrito){
        DistritoModel distrito = iDistritoRepository.getReferenceById(idDistrito);
        distrito.desactivarDistrito();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ver/{idDistrito}")
    @Transactional
    public ResponseEntity<?> listadoDistritoId(@PathVariable Long idDistrito){
        DistritoModel distrito = iDistritoRepository.getReferenceById(idDistrito);
        var datosDistrito = new DatosRespuestaDistritoDto(
                distrito.getIdDistrito(),
                distrito.getCodigoDistrito(),
                distrito.getNombreDistrito(),
                distrito.getParroquia());

        return ResponseEntity.ok(datosDistrito);
    }

    @GetMapping("/listardistritos")
    public List<DistritoModel> list(){
        return iDistritoRepository.findAll();
    }


}
