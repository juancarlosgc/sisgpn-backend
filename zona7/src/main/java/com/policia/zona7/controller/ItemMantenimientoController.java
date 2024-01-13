package com.policia.zona7.controller;

import com.policia.zona7.dto.circuito.DatosListadoCircuitoDto;
import com.policia.zona7.dto.item.DatosActualizarItemDto;
import com.policia.zona7.dto.item.DatosListadoItemDto;
import com.policia.zona7.dto.item.DatosRegistroItemDto;
import com.policia.zona7.dto.item.DatosRespuestaItemDto;
import com.policia.zona7.dto.mantenimiento.DatosRegistroMantenimientoDto;
import com.policia.zona7.dto.mantenimiento.DatosRespuestaMantenimientoDto;
import com.policia.zona7.dto.subcircuito.DatosActualizarSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosListadoSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosRespuestaSubcircuitoDto;
import com.policia.zona7.model.CircuitoModel;
import com.policia.zona7.model.ItemMantenimientoModel;
import com.policia.zona7.model.MantenimientoModel;
import com.policia.zona7.model.SubcircuitoModel;
import com.policia.zona7.repository.IItemMantenimientoRepository;
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
import java.util.List;

@RestController
@RequestMapping("items")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemMantenimientoController {

    @Autowired
    private IItemMantenimientoRepository iItemMantenimientoRepository;

    @Autowired
    private IMantenimientoRepository iMantenimientoRepository;

    @PostMapping("/crear")
    public ResponseEntity<DatosRespuestaItemDto> guardarItem(@RequestBody @Valid DatosRegistroItemDto datosRegistroItemDto,UriComponentsBuilder uriComponentsBuilder){
        var idMantenimiento= iMantenimientoRepository.findById(datosRegistroItemDto.idMantenimiento()).get();
        var itemMantenimiento=new ItemMantenimientoModel(
                null,
                datosRegistroItemDto.codigoItem(),
                datosRegistroItemDto.nombreItem(),
                datosRegistroItemDto.descripcionItem(),
                true,
                idMantenimiento);
        iItemMantenimientoRepository.save(itemMantenimiento);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/vertodo")
    public ResponseEntity<Page<DatosListadoItemDto>> listadoItem(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iItemMantenimientoRepository.findByEstaActivoTrue(paginacion).map(DatosListadoItemDto::new));
    }

    @PutMapping("/editar")
    @Transactional
    public ResponseEntity<DatosRespuestaItemDto> actualizarItem(@RequestBody @Valid DatosActualizarItemDto datos){
        ItemMantenimientoModel item= iItemMantenimientoRepository.getReferenceById(datos.idItem());
        var idMantenimiento=iMantenimientoRepository.getReferenceById(datos.idMantenimiento());
        var itemEditado= new ItemMantenimientoModel(item.getIdItem(), datos.codigoItem(),datos.nombreItem(), datos.descripcionItem(),true, idMantenimiento);
        iItemMantenimientoRepository.save(itemEditado);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/eliminar/{idItem}")
    @Transactional
    public ResponseEntity eliminarSubcircuito(@PathVariable Long idItem){
        ItemMantenimientoModel item = iItemMantenimientoRepository.getReferenceById(idItem);
        item.desactivarItem();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ver/{idItem}")
    @Transactional
    public ResponseEntity<?> listadoItemId(@PathVariable Long idItem){
        ItemMantenimientoModel item = iItemMantenimientoRepository.getReferenceById(idItem);
        var datosItem = new DatosRespuestaItemDto(
                item.getIdItem(),
                item.getCodigoItem(),
                item.getNombreItem(),
                item.getDescripcionItem(),
                item.getMantenimiento().getIdMantenimiento());
        return ResponseEntity.ok(datosItem);
    }

    @GetMapping("/listarmantenimientos")
    public List<MantenimientoModel> list(){
        return iMantenimientoRepository.findAll();
    }
}
