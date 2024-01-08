package com.policia.zona7.controller;


import com.policia.zona7.dto.circuito.*;
import com.policia.zona7.model.CircuitoModel;
import com.policia.zona7.model.DistritoModel;
import com.policia.zona7.repository.ICircuitoRepository;
import com.policia.zona7.repository.IDistritoRepository;
import com.policia.zona7.service.CircuitoDistritoService;
import com.policia.zona7.service.CircuitoService;
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
@RequestMapping("circuitos")
@CrossOrigin(origins = "http://localhost:4200")
public class CircuitoController {

    @Autowired
    private CircuitoDistritoService service;

    @Autowired
    private IDistritoRepository iDistritoRepository;

    @Autowired
    private ICircuitoRepository iCircuitoRepository;

    @Autowired
    private CircuitoService circuitoService;


    @PostMapping("/crearcircuito")
    @Transactional
    public ResponseEntity circuitoasignar(@RequestBody @Valid DatosRegistroCircuitoDto datos) {
        service.guardar(datos);
        return ResponseEntity.ok(new DatosDetalleCircuitoDto(null, datos.codigoCircuito(), datos.nombreCircuito(), datos.idDistrito()));
    }


    @GetMapping("/vertodo")
    public ResponseEntity<Page<DatosListadoCircuitoDto>> listadoCircuito(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iCircuitoRepository.obtenerCircuitosConDistritos(paginacion).map(DatosListadoCircuitoDto::new));
    }

    @GetMapping("/ver/{idCircuito}")
    @Transactional
    public ResponseEntity<?> listadoCircuitoId(@PathVariable Long idCircuito) {
        CircuitoModel circuito = iCircuitoRepository.getReferenceById(idCircuito);
        var datosCircuito = new DatosRespuestaCircuitoDto(
                circuito.getIdCircuito(),
                circuito.getCodigoCircuito(),
                circuito.getNombreCircuito(),
                circuito.getDistrito().getIdDistrito());
        return ResponseEntity.ok(datosCircuito);
    }

    @DeleteMapping("/eliminar/{idCircuito}")
    @Transactional
    public ResponseEntity eliminarCircuito(@PathVariable Long idCircuito){
        CircuitoModel circuito = iCircuitoRepository.getReferenceById(idCircuito);
        circuito.desactivarCircuito();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editar")
    @Transactional
    public void actualizarCircuito(@RequestBody @Valid DatosActualizarCircuitoDto datos){
        service.editar(datos);
    }

  /*  @GetMapping("/listarcircuitos")
    public List<CircuitoModel> list(){
        return iCircuitoRepository.findAll();
    }*/

    @GetMapping("/listardistritos")
    public List<DistritoModel> list(){
        return iDistritoRepository.findAll();
    }



   /* @GetMapping("/condistritos")
    public  void  obtenerClientesConPedidos() {
        iCircuitoRepository.obtenerClientesConPedidos();

    }*/
}
