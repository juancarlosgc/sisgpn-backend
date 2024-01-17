package com.policia.zona7.repository;

import com.policia.zona7.dto.reclamos.DatosListadoReclamoDto;
import com.policia.zona7.model.ReclamosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface IReclamosRepository extends JpaRepository<ReclamosModel, Long> {

    List<ReclamosModel> findByFechaBetween(Date fechaInicio, Date fechaFin);
}
