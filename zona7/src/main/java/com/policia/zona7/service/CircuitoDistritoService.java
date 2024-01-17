package com.policia.zona7.service;

import com.policia.zona7.dto.circuito.*;
import com.policia.zona7.model.CircuitoModel;
import com.policia.zona7.repository.ICircuitoRepository;
import com.policia.zona7.repository.IDistritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CircuitoDistritoService {

    @Autowired
    private IDistritoRepository iDistritoRepository;

    @Autowired
    private ICircuitoRepository iCircuitoRepository;

    public void guardar(DatosRegistroCircuitoDto datosRegistroCircuitoDto){
        var distrito=iDistritoRepository.findById(datosRegistroCircuitoDto.idDistrito()).get();
        var distritoCircuito= new CircuitoModel(null, datosRegistroCircuitoDto.codigoCircuito(), datosRegistroCircuitoDto.nombreCircuito(),true, distrito);
        iCircuitoRepository.save(distritoCircuito);
    }

    public void editar(DatosActualizarCircuitoDto datosActualizarCircuitoDto){
        CircuitoModel circuito  = iCircuitoRepository.getReferenceById(datosActualizarCircuitoDto.idCircuito());
        var distrito=iDistritoRepository.getReferenceById(datosActualizarCircuitoDto.idDistrito());
        var circuitoEditado= new CircuitoModel(circuito.getIdCircuito(), datosActualizarCircuitoDto.codigoCircuito(), datosActualizarCircuitoDto.nombreCircuito(),true, distrito);
        iCircuitoRepository.save(circuitoEditado);
      }
   }
