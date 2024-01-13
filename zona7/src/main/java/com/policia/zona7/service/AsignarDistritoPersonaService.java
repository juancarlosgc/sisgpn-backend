package com.policia.zona7.service;

import com.policia.zona7.dto.asignaciones.DatosActualizarDistritoPersonaDto;
import com.policia.zona7.dto.asignaciones.DatosAsignarDistritoPersonaDto;
import com.policia.zona7.dto.subcircuito.DatosActualizarSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosRegistroSubcircuitoDto;
import com.policia.zona7.infra.ValidacionIntegridad;
import com.policia.zona7.model.DistritoPersonaModel;
import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.model.SubcircuitoModel;
import com.policia.zona7.repository.IDistritoPersonaRepository;
import com.policia.zona7.repository.IPersonaRepository;
import com.policia.zona7.repository.ISubcircuitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignarDistritoPersonaService {

    @Autowired
    private IDistritoPersonaRepository iDistritoPersonaRepository;

    @Autowired
    private IPersonaRepository iPersonaRepository;

    @Autowired
    private ISubcircuitoRepository iSubcircuitoRepository;

    public void guardar(DatosAsignarDistritoPersonaDto datos){

       /* if (iPersonaRepository.findById(datos.idPersona()).isPresent()){
            throw  new ValidacionIntegridad("Persona ya tiene Distrito Asignado");
        }*/

        var persona=iPersonaRepository.findById(datos.idPersona()).get();
        var subcircuito=iSubcircuitoRepository.findById(datos.idSubcircuito()).get();
        var datosasignacion= new DistritoPersonaModel(null, persona, subcircuito, datos.fecha());

        iDistritoPersonaRepository.save(datosasignacion);

    }

    public void editar(DatosActualizarDistritoPersonaDto datosActualizarDistritoPersonaDto){
        DistritoPersonaModel distritopersona  = iDistritoPersonaRepository.getReferenceById(datosActualizarDistritoPersonaDto.idDistritoPersona());
        var persona=iPersonaRepository.getReferenceById(datosActualizarDistritoPersonaDto.idPersona());
        var subcircuito=iSubcircuitoRepository.getReferenceById(datosActualizarDistritoPersonaDto.idSubcircuito());
        var distritopersonaEditado= new DistritoPersonaModel(distritopersona.getIdDistritoPersona(), persona, subcircuito,datosActualizarDistritoPersonaDto.fecha());
        iDistritoPersonaRepository.save(distritopersonaEditado);
    }

}
