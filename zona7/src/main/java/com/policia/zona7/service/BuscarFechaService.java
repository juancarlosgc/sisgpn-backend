package com.policia.zona7.service;

import com.policia.zona7.dto.reclamos.DatosListadoReclamoDto;
import com.policia.zona7.model.ReclamosModel;
import com.policia.zona7.repository.IReclamosDetalleRepository;
import com.policia.zona7.repository.IReclamosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BuscarFechaService {

    @Autowired
    private IReclamosRepository iReclamosRepository;

    @Autowired
    private IReclamosDetalleRepository iReclamosDetalleRepository;

   /* public List<ReclamosModel> buscarPorRangoDeFechas(Date fechaInicio, Date fechaFin) {
        return iReclamosRepository.findByFechaBetween(fechaInicio, fechaFin);
    }*/

    public List<ReclamosModel> buscarPorRangoDeFechas(Date fechaInicio, Date fechaFin) {
        List<ReclamosModel> registros = iReclamosRepository.findByFechaBetween(fechaInicio, fechaFin);
        return registros;
    }




}
