package com.policia.zona7.repository;

import com.policia.zona7.model.MantenimientoModel;
import com.policia.zona7.model.PersonaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMantenimientoRepository extends JpaRepository<MantenimientoModel, Long> {
    Page<MantenimientoModel> findByEstaActivoTrue(Pageable paginacion);
}
