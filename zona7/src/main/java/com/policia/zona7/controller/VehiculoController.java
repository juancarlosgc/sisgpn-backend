package com.policia.zona7.controller;

import com.policia.zona7.dto.persona.DatosActualizarPersonaDto;
import com.policia.zona7.dto.persona.DatosListadoPersonaDto;
import com.policia.zona7.dto.persona.DatosRegistroPersonaDto;
import com.policia.zona7.dto.persona.DatosRespuestaPersonaDto;
import com.policia.zona7.dto.vehiculo.DatosActualizarVehiculoDto;
import com.policia.zona7.dto.vehiculo.DatosListadoVehiculoDto;
import com.policia.zona7.dto.vehiculo.DatosRegistroVehiculoDto;
import com.policia.zona7.dto.vehiculo.DatosRespuestaVehiculoDto;
import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.model.VehiculoModel;
import com.policia.zona7.repository.IVehiculoRepository;
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
@RequestMapping("vehiculos")
public class VehiculoController {

    @Autowired
    private IVehiculoRepository iVehiculoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaVehiculoDto> resgistrarVehiculo(@RequestBody @Valid DatosRegistroVehiculoDto datosRegistroVehiculoDto,
                                                                       UriComponentsBuilder uriComponentsBuilder){
        VehiculoModel vehiculo = iVehiculoRepository.save(new VehiculoModel(datosRegistroVehiculoDto));
        DatosRespuestaVehiculoDto datosRespuestaVehiculoDto = new DatosRespuestaVehiculoDto(
                vehiculo.getIdVehiculo(),
                vehiculo.getPlaca(),
                vehiculo.getChasis(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getMotor(),
                vehiculo.getKilometraje(),
                vehiculo.getCilindraje(),
                vehiculo.getCapacidadCarga(),
                vehiculo.getCantidadPasajeros(),
                vehiculo.getTipoVehiculo(),
                vehiculo.getObservaciones()
        );
        URI url = uriComponentsBuilder.path("/personas/{idVehiculo}").buildAndExpand(vehiculo.getIdVehiculo()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaVehiculoDto);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoVehiculoDto>> listadoMedicos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iVehiculoRepository.findByEstaActivoTrue(paginacion).map(DatosListadoVehiculoDto::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarVehiculo(@RequestBody @Valid DatosActualizarVehiculoDto datosActualizarVehiculoDto){
        VehiculoModel vehiculo  =iVehiculoRepository.getReferenceById(datosActualizarVehiculoDto.idVehiculo());
        vehiculo.actualizarDatosVehiculo(datosActualizarVehiculoDto);
        return ResponseEntity.ok(new DatosRespuestaVehiculoDto(
               vehiculo.getIdVehiculo(),
                vehiculo.getPlaca(),
                vehiculo.getChasis(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getMotor(),
                vehiculo.getKilometraje(),
                vehiculo.getCilindraje(),
                vehiculo.getCapacidadCarga(),
                vehiculo.getCantidadPasajeros(),
                vehiculo.getTipoVehiculo(),
                vehiculo.getObservaciones()

        ));
    }

    @DeleteMapping("/{idVehiculo}")
    @Transactional
    public ResponseEntity eliminarVehiculo(@PathVariable Long idVehiculo){
        VehiculoModel vehiculo = iVehiculoRepository.getReferenceById(idVehiculo);
        vehiculo.desactivarVehiculo();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idVehiculo}")
    @Transactional
    public ResponseEntity<DatosRespuestaVehiculoDto> listadoVehiculoId(@PathVariable Long idVehiculo){
        VehiculoModel vehiculo = iVehiculoRepository.getReferenceById(idVehiculo);
        var datosVehiculo = new DatosRespuestaVehiculoDto(vehiculo.getIdVehiculo(),
                vehiculo.getPlaca(),
                vehiculo.getChasis(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getMotor(),
                vehiculo.getKilometraje(),
                vehiculo.getCilindraje(),
                vehiculo.getCapacidadCarga(),
                vehiculo.getCantidadPasajeros(),
                vehiculo.getTipoVehiculo(),
                vehiculo.getObservaciones());
        return ResponseEntity.ok(datosVehiculo);
    }
}
