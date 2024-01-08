package com.policia.zona7.service;


import com.policia.zona7.dto.circuito.DatosActualizarCircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosActualizarSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosRegistroSubcircuitoDto;
import com.policia.zona7.model.CircuitoModel;
import com.policia.zona7.model.SubcircuitoModel;
import com.policia.zona7.repository.ICircuitoRepository;
import com.policia.zona7.repository.ISubcircuitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubcircuitoCircuitoService {

    @Autowired
    private ICircuitoRepository iCircuitoRepository;

    @Autowired
    private ISubcircuitoRepository iSubcircuitoRepository;

    public void guardar(DatosRegistroSubcircuitoDto datosRegistroSubcircuitoDto){
        var circuito=iCircuitoRepository.findById(datosRegistroSubcircuitoDto.idCircuito()).get();
        var circuitoSubcircuito= new SubcircuitoModel(null, datosRegistroSubcircuitoDto.codigoSubcircuito(), datosRegistroSubcircuitoDto.nombreSubcircuito(),true, circuito,null,null);
        iSubcircuitoRepository.save(circuitoSubcircuito);
    }

    public void editar(DatosActualizarSubcircuitoDto datosActualizarSubcircuitoDto){
        SubcircuitoModel subcircuito  = iSubcircuitoRepository.getReferenceById(datosActualizarSubcircuitoDto.idSubcircuito());
        var circuito=iCircuitoRepository.getReferenceById(datosActualizarSubcircuitoDto.idCircuito());
        var subcircuitoEditado= new SubcircuitoModel(subcircuito.getIdSubcircuito(), datosActualizarSubcircuitoDto.codigoSubcircuito(), datosActualizarSubcircuitoDto.nombreSubcircuito(),true, circuito,null,null);
        iSubcircuitoRepository.save(subcircuitoEditado);
    }

}

