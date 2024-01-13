package com.policia.zona7.controller;

import com.policia.zona7.dto.mantenimiento.DatosActualizarMantenimientoDto;
import com.policia.zona7.dto.mantenimiento.DatosListadoMantenimientoDto;
import com.policia.zona7.dto.mantenimiento.DatosRegistroMantenimientoDto;
import com.policia.zona7.dto.mantenimiento.DatosRespuestaMantenimientoDto;
import com.policia.zona7.model.MantenimientoModel;
import com.policia.zona7.repository.IMantenimientoRepository;
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
@RequestMapping("mantenimientos")
@CrossOrigin(origins = "http://localhost:4200")
public class MantenimientoController {

    @Autowired
    private IMantenimientoRepository iMantenimientoRepository;

    @PostMapping("/crear")
    public ResponseEntity<DatosRespuestaMantenimientoDto> resgistrarMantenimiento(@RequestBody @Valid DatosRegistroMantenimientoDto datosRegistroMantenimientoDto,
                                                                            UriComponentsBuilder uriComponentsBuilder){
        MantenimientoModel mantenimiento = iMantenimientoRepository.save(new MantenimientoModel(datosRegistroMantenimientoDto));
        DatosRespuestaMantenimientoDto datosRespuestaMantenimientoDto = new DatosRespuestaMantenimientoDto(
                mantenimiento.getIdMantenimiento(),
                mantenimiento.getCodigoMantenimiento(),
                mantenimiento.getNombreMantenimiento(),
                mantenimiento.getCostoMantenimiento()
        );
        URI url = uriComponentsBuilder.path("/mantenimiento/{idMantenimiento}").buildAndExpand(mantenimiento.getIdMantenimiento()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMantenimientoDto);
    }

    @GetMapping("/vertodo")
    public ResponseEntity<Page<DatosListadoMantenimientoDto>> listadoMantenimientos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iMantenimientoRepository.findByEstaActivoTrue(paginacion).map(DatosListadoMantenimientoDto::new));
    }

    @PutMapping("/editar")
    @Transactional
    public ResponseEntity actualizarMantenimiento(@RequestBody @Valid DatosActualizarMantenimientoDto datosActualizarMantenimientoDto){
        MantenimientoModel mantenimiento  =iMantenimientoRepository.getReferenceById(datosActualizarMantenimientoDto.idMantenimiento());
        mantenimiento.actualizarDatosMantenimiento(datosActualizarMantenimientoDto);
        return ResponseEntity.ok(new DatosRespuestaMantenimientoDto(
                mantenimiento.getIdMantenimiento(),
                mantenimiento.getCodigoMantenimiento(),
                mantenimiento.getNombreMantenimiento(),
                mantenimiento.getCostoMantenimiento()
        ));
    }

    @DeleteMapping("/eliminar/{idMantenimiento}")
    @Transactional
    public ResponseEntity eliminarMantenimiento(@PathVariable Long idMantenimiento){
        MantenimientoModel mantenimiento = iMantenimientoRepository.getReferenceById(idMantenimiento);
        mantenimiento.desactivarMantenimiento();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ver/{idMantenimiento}")
    @Transactional
    public ResponseEntity<?> listadoMantenimientoId(@PathVariable Long idMantenimiento){
        MantenimientoModel mantenimiento = iMantenimientoRepository.getReferenceById(idMantenimiento);
        var datosMantenimiento = new DatosRespuestaMantenimientoDto(
                mantenimiento.getIdMantenimiento(),
                mantenimiento.getCodigoMantenimiento(),
                mantenimiento.getNombreMantenimiento(),
                mantenimiento.getCostoMantenimiento()
        );
        return ResponseEntity.ok(datosMantenimiento);
    }
}
