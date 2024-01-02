package com.policia.zona7.controller;

import com.policia.zona7.dto.circuito.DatosActualizarCircuitoDto;
import com.policia.zona7.dto.circuito.DatosListadoCircuitoDto;
import com.policia.zona7.dto.circuito.DatosRegistroCircuitoDto;
import com.policia.zona7.dto.circuito.DatosRespuestaCircuitoDto;
import com.policia.zona7.model.CircuitoModel;
import com.policia.zona7.repository.ICircuitoRepository;
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
@RequestMapping("circuitos")
@CrossOrigin(origins = "http://localhost:4200")
public class CircuitoController {

    @Autowired
    private ICircuitoRepository iCircuitoRepository;

    @PostMapping("/crearcircuito")
    public ResponseEntity<DatosRespuestaCircuitoDto> resgistrarCircuito(@RequestBody @Valid DatosRegistroCircuitoDto datosRegistroCircuitoDto,
                                                                        UriComponentsBuilder uriComponentsBuilder){
        CircuitoModel circuito = iCircuitoRepository.save(new CircuitoModel(datosRegistroCircuitoDto));
        DatosRespuestaCircuitoDto datosRespuestaCircuitoDto = new DatosRespuestaCircuitoDto(
                circuito.getIdCircuito(),
                circuito.getCodigoCircuito(),
                circuito.getNombreCircuito()
        );
        URI url = uriComponentsBuilder.path("/circuitos/{idCircuito}").buildAndExpand(circuito.getIdCircuito()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCircuitoDto);
    }

    @GetMapping("/vertodo")
    public ResponseEntity<Page<DatosListadoCircuitoDto>> listadoCircuito(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iCircuitoRepository.findByEstaActivoTrue(paginacion).map(DatosListadoCircuitoDto::new));
    }

    @PutMapping("/editar")
    @Transactional
    public ResponseEntity actualizarCircuito(@RequestBody @Valid DatosActualizarCircuitoDto datosActualizarCircuitoDto){
        CircuitoModel circuito  =iCircuitoRepository.getReferenceById(datosActualizarCircuitoDto.idCircuito());
        circuito.actualizarDatosCircuito(datosActualizarCircuitoDto);
        return ResponseEntity.ok(new DatosRespuestaCircuitoDto(
                circuito.getIdCircuito(),
                circuito.getCodigoCircuito(),
                circuito.getNombreCircuito()
        ));
    }

    @DeleteMapping("/eliminar/{idCircuito}")
    @Transactional
    public ResponseEntity eliminarCircuito(@PathVariable Long idCircuito){
        CircuitoModel circuito = iCircuitoRepository.getReferenceById(idCircuito);
        circuito.desactivarCircuito();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ver/{idCircuito}")
    @Transactional
    public ResponseEntity<?> listadoCircuitoId(@PathVariable Long idCircuito){
        CircuitoModel circuito = iCircuitoRepository.getReferenceById(idCircuito);
        var datosCircuito = new DatosRespuestaCircuitoDto(
                circuito.getIdCircuito(),
                circuito.getCodigoCircuito(),
                circuito.getNombreCircuito());
        return ResponseEntity.ok(datosCircuito);
    }
}
