package com.policia.zona7.controller;

import com.policia.zona7.dto.circuito.DatosActualizarCircuitoDto;
import com.policia.zona7.dto.circuito.DatosListadoCircuitoDto;
import com.policia.zona7.dto.circuito.DatosRegistroCircuitoDto;
import com.policia.zona7.dto.circuito.DatosRespuestaCircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosActualizarSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosListadoSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosRegistroSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosRespuestaSubcircuitoDto;
import com.policia.zona7.model.CircuitoModel;
import com.policia.zona7.model.SubcircuitoModel;
import com.policia.zona7.repository.ICircuitoRepository;
import com.policia.zona7.repository.ISubcircuitoRepository;
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
@RequestMapping("subcircuitos")
@CrossOrigin(origins = "http://localhost:4200")
public class SubcircuitoController {

    @Autowired
    private ISubcircuitoRepository iSubcircuitoRepository;

    @PostMapping("/crearsubcircuito")
    public ResponseEntity<DatosRespuestaSubcircuitoDto> resgistrarSubcircuito(@RequestBody @Valid DatosRegistroSubcircuitoDto datosRegistroSubcircuitoDto,
                                                                           UriComponentsBuilder uriComponentsBuilder){
        SubcircuitoModel subcircuito = iSubcircuitoRepository.save(new SubcircuitoModel(datosRegistroSubcircuitoDto));
        DatosRespuestaSubcircuitoDto datosRespuestaSubcircuitoDto = new DatosRespuestaSubcircuitoDto(
                subcircuito.getIdSubcircuito(),
                subcircuito.getCodigoSubcircuito(),
                subcircuito.getNombreSubcircuito()
        );
        URI url = uriComponentsBuilder.path("/subcircuitos/{idSubcircuito}").buildAndExpand(subcircuito.getIdSubcircuito()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaSubcircuitoDto);
    }


    @GetMapping("/vertodo")
    public ResponseEntity<Page<DatosListadoSubcircuitoDto>> listadoSubcircuito(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iSubcircuitoRepository.findByEstaActivoTrue(paginacion).map(DatosListadoSubcircuitoDto::new));
    }

    @PutMapping("/editar")
    @Transactional
    public ResponseEntity actualizarSubcircuito(@RequestBody @Valid DatosActualizarSubcircuitoDto datosActualizarSubcircuitoDto){
        SubcircuitoModel subcircuito  =iSubcircuitoRepository.getReferenceById(datosActualizarSubcircuitoDto.idSubcircuito());
        subcircuito.actualizarDatosSubcircuito(datosActualizarSubcircuitoDto);
        return ResponseEntity.ok(new DatosRespuestaSubcircuitoDto(
                subcircuito.getIdSubcircuito(),
                subcircuito.getCodigoSubcircuito(),
                subcircuito.getNombreSubcircuito()
        ));
    }


    @DeleteMapping("/eliminar/{idSubcircuito}")
    @Transactional
    public ResponseEntity eliminarSubcircuito(@PathVariable Long idSubcircuito){
        SubcircuitoModel subcircuito = iSubcircuitoRepository.getReferenceById(idSubcircuito);
        subcircuito.desactivarSubcircuito();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ver/{idSubcircuito}")
    @Transactional
    public ResponseEntity<?> listadoCircuitoId(@PathVariable Long idSubcircuito){
        SubcircuitoModel subcircuito = iSubcircuitoRepository.getReferenceById(idSubcircuito);
        var datosSubcircuito = new DatosRespuestaCircuitoDto(
                subcircuito.getIdSubcircuito(),
                subcircuito.getCodigoSubcircuito(),
                subcircuito.getNombreSubcircuito());
        return ResponseEntity.ok(datosSubcircuito);
    }


}
