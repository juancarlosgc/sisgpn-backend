package com.policia.zona7.repository;

import com.policia.zona7.dto.subcircuito.DatosListadoSubcircuitoDto;
import com.policia.zona7.model.SubcircuitoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISubcircuitoRepository extends JpaRepository<SubcircuitoModel, Long> {

    Page<SubcircuitoModel> findByEstaActivoTrue(Pageable paginacion);


}
