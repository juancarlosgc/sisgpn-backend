package com.policia.zona7.controller;

import com.policia.zona7.dto.asignaciones.*;
import com.policia.zona7.dto.item.DatosListadoItemDto;
import com.policia.zona7.dto.subcircuito.DatosActualizarSubcircuitoDto;
import com.policia.zona7.model.*;
import com.policia.zona7.repository.IDistritoVehiculoRepository;
import com.policia.zona7.repository.IPersonaRepository;
import com.policia.zona7.repository.ISubcircuitoRepository;
import com.policia.zona7.repository.IVehiculoRepository;
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
@RequestMapping("distritosvehiculos")
@CrossOrigin(origins = "http://localhost:4200")
public class AsignaDistritoAVehiculoController {

    @Autowired
    private IDistritoVehiculoRepository iDistritoVehiculoRepository;

    @Autowired
    private ISubcircuitoRepository iSubcircuitoRepository;

    @Autowired
    private IVehiculoRepository iVehiculoRepository;

    @PostMapping("/crear")
    @Transactional
    public ResponseEntity asinarDistritoVehiculo(@RequestBody @Valid DatosAsignarDistritoVehiculoDto datos){
        var idSubcircuito= iSubcircuitoRepository.findById(datos.idSubcircuito()).get();
        var idVehiculo= iVehiculoRepository.findById(datos.idVehiculo()).get();
        var distritoVehiculo=new DistritoVehiculoModel(
                null,
                idVehiculo,
                idSubcircuito,
                datos.fecha());
        iDistritoVehiculoRepository.save(distritoVehiculo);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/vertodo")
    public ResponseEntity<Page<DatosListadoDistritoVehiculoDto>> listadoDistritoVehiculo(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iDistritoVehiculoRepository.findAll(paginacion).map(DatosListadoDistritoVehiculoDto::new));
    }

    @PutMapping("/editar")
    @Transactional
    public ResponseEntity actualizarDistritoVehiculo(@RequestBody @Valid DatosActualizarDistritoVehiculoDto datos){
        DistritoVehiculoModel distritoVehiculo=iDistritoVehiculoRepository.getReferenceById(datos.idDistritoVehiculo());
        var subcircuito=iSubcircuitoRepository.getReferenceById(datos.idSubcircuito());
        var vehiculo= iVehiculoRepository.getReferenceById(datos.idVehiculo());
        var distritoVehiculoEditado=new DistritoVehiculoModel(distritoVehiculo.getIdDistritoVehiculo(),vehiculo,subcircuito,distritoVehiculo.getFecha());
        iDistritoVehiculoRepository.save(distritoVehiculoEditado);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ver/{idDistritoVehiculo}")
    @Transactional
    public ResponseEntity<?> listadoDistritoPersonaId(@PathVariable Long idDistritoVehiculo){
        DistritoVehiculoModel distritoVehiculo = iDistritoVehiculoRepository.getReferenceById(idDistritoVehiculo);
        var datosRespuestaDistritoVehiculoDto= new DatosRespuestaDistritoVehiculoDto(
                distritoVehiculo.getIdDistritoVehiculo(),
                distritoVehiculo.getVehiculo().getIdVehiculo(),
                distritoVehiculo.getSubcircuito().getIdSubcircuito(),
                distritoVehiculo.getFecha()
        );
        return ResponseEntity.ok(datosRespuestaDistritoVehiculoDto);
    }

    @DeleteMapping("/eliminar/{idDistritoVehiculo}")
    @Transactional
    public ResponseEntity eliminarSubcircuito(@PathVariable Long idDistritoVehiculo){
        DistritoVehiculoModel distritoVehiculoModel = iDistritoVehiculoRepository.getReferenceById(idDistritoVehiculo);
        // distritoPersonaModel.desactivarSubcircuito();
        iDistritoVehiculoRepository.delete(distritoVehiculoModel);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listarvehiculos")
    public List<VehiculoModel> listvehiculos(){
        return iVehiculoRepository.findAll();
    }

    @GetMapping("/listarsubcircuitos")
    public List<SubcircuitoModel>  listsubcircuitos (){
        return iSubcircuitoRepository.findAll();
    }

}
