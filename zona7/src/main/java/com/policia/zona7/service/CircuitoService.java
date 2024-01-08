package com.policia.zona7.service;

import com.policia.zona7.model.CircuitoModel;
import com.policia.zona7.model.DistritoModel;
import com.policia.zona7.repository.ICircuitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircuitoService {

    @Autowired
    private ICircuitoRepository iCircuitoRepository;

    public List<CircuitoModel> obtenerDistritosConCircuitos() {
        return iCircuitoRepository.findAll();

    }
}
