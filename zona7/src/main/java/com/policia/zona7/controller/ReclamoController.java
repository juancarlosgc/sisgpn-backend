package com.policia.zona7.controller;

import com.policia.zona7.dto.asignaciones.DatosAsignarDistritoVehiculoDto;
import com.policia.zona7.dto.asignaciones.DatosListadoDistritoVehiculoDto;
import com.policia.zona7.dto.reclamos.DatosAsignarReclamoDto;
import com.policia.zona7.dto.reclamos.DatosListadoReclamoDto;
import com.policia.zona7.dto.subcircuito.DatosDetalleSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosRegistroSubcircuitoDto;
import com.policia.zona7.model.*;
import com.policia.zona7.repository.ICircuitoRepository;
import com.policia.zona7.repository.IReclamosRepository;
import com.policia.zona7.repository.ISubcircuitoRepository;
import com.policia.zona7.service.BuscarFechaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("reclamos")
@CrossOrigin(origins = "http://localhost:4200")
public class ReclamoController {

    @Autowired
    private IReclamosRepository iReclamosRepository;

    @Autowired
    private ISubcircuitoRepository iSubcircuitoRepository;

    @Autowired
    private ICircuitoRepository iCircuitoRepository;

    @Autowired
    private BuscarFechaService buscarFechaService;


    @PostMapping("/crear")
    @Transactional
    public ResponseEntity asinarDistritoVehiculo(@RequestBody @Valid DatosAsignarReclamoDto datos){
        var idSubcircuito= iSubcircuitoRepository.findById(datos.idSubcircuito()).get();
        var idCircuito= iCircuitoRepository.findById(datos.idCircuito()).get();
        var reclamos=new ReclamosModel(
                null,
                datos.tipoIncidente(),
                datos.detalle(),
                datos.contacto(),
                datos.apellidos(),
                datos.nombres(),
                datos.fecha(),
                idSubcircuito,
                idCircuito
      );
        iReclamosRepository.save(reclamos);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/vertodo")
    public ResponseEntity<Page<DatosListadoReclamoDto>> listadoReclamos(
            @PageableDefault(size = 5) Pageable paginacion)


    {

        return ResponseEntity.ok(iReclamosRepository.findAll(paginacion).map(DatosListadoReclamoDto::new));

    }

    @GetMapping("/listarcircuitos")
    public List<CircuitoModel> listcircuitos(){
        return iCircuitoRepository.findAll();
    }

    @GetMapping("/listarsubcircuitos")
    public List<SubcircuitoModel> listsubcircuitos(){
        return iSubcircuitoRepository.findAll();
    }

 /*   @GetMapping("/fechas")
    public List<ReclamosModel> buscarPorRangoFechas(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {

        return buscarFechaService.buscarPorRangoDeFechas(fechaInicio, fechaFin);
    }*/

    @GetMapping("/fechas")
    public ResponseEntity<List<ReclamosModel>> buscarPorRangoFechas(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        try {
            List<ReclamosModel> registros = buscarFechaService.buscarPorRangoDeFechas(fechaInicio, fechaFin);

            return new ResponseEntity<>(registros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
