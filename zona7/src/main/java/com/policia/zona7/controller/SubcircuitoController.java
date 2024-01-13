package com.policia.zona7.controller;


import com.policia.zona7.dto.circuito.DatosActualizarCircuitoDto;
import com.policia.zona7.dto.circuito.DatosDetalleCircuitoDto;
import com.policia.zona7.dto.circuito.DatosRegistroCircuitoDto;
import com.policia.zona7.dto.subcircuito.*;
import com.policia.zona7.model.CircuitoModel;
import com.policia.zona7.model.DistritoModel;
import com.policia.zona7.model.SubcircuitoModel;
import com.policia.zona7.repository.ICircuitoRepository;
import com.policia.zona7.repository.ISubcircuitoRepository;
import com.policia.zona7.service.CircuitoDistritoService;
import com.policia.zona7.service.SubcircuitoCircuitoService;
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
@RequestMapping("subcircuitos")
@CrossOrigin(origins = "http://localhost:4200")
public class SubcircuitoController {

    @Autowired
    private ISubcircuitoRepository iSubcircuitoRepository;

    @Autowired
    private SubcircuitoCircuitoService service;

    @Autowired
    private ICircuitoRepository iCircuitoRepository;

    @PostMapping("/crearsubcircuito")
    @Transactional
    public ResponseEntity subcircuitoasignar(@RequestBody @Valid DatosRegistroSubcircuitoDto datos) {
        service.guardar(datos);
        return ResponseEntity.ok(new DatosDetalleSubcircuitoDto(null, datos.codigoSubcircuito(), datos.nombreSubcircuito(), datos.idCircuito()));
    }


    @GetMapping("/vertodo")
    public ResponseEntity<Page<DatosListadoSubcircuitoDto>> listadoSubcircuito(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iSubcircuitoRepository.findByEstaActivoTrue(paginacion).map(DatosListadoSubcircuitoDto::new));
    }


    @PutMapping("/editar")
    @Transactional
    public void actualizarCircuito(@RequestBody @Valid DatosActualizarSubcircuitoDto datos){
        service.editar(datos);
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
        var datosSubcircuito = new DatosRespuestaSubcircuitoDto(
                subcircuito.getIdSubcircuito(),
                subcircuito.getCodigoSubcircuito(),
                subcircuito.getNombreSubcircuito(),
                subcircuito.getCircuito().getIdCircuito());
        return ResponseEntity.ok(datosSubcircuito);
    }

    @GetMapping("/listarcircuitos")
    public List<CircuitoModel> list(){
        return iCircuitoRepository.findAll();
    }
}
