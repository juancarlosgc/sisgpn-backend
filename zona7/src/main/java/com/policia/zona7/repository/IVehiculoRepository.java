package com.policia.zona7.repository;

import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.model.VehiculoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehiculoRepository extends JpaRepository<VehiculoModel, Long> {
    Page<VehiculoModel> findByEstaActivoTrue(Pageable paginacion);

}
