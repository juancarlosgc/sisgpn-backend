package com.policia.zona7.service;

import com.policia.zona7.dto.persona.DatosListadoPersonaDto;
import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    IPersonaRepository iPersonaRepository;

    public ResponseEntity<Page<DatosListadoPersonaDto>> listadoMedicos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(iPersonaRepository.findByEstaActivoTrue(paginacion).map(DatosListadoPersonaDto::new));
    }

    public Optional<PersonaModel> buscarPorApellido(String apellido){
            return iPersonaRepository.findByApellidos(apellido);
    }


}
