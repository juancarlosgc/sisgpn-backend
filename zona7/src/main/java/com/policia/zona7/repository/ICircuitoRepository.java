package com.policia.zona7.repository;

import com.policia.zona7.model.CircuitoModel;
import com.policia.zona7.model.DistritoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICircuitoRepository extends JpaRepository<CircuitoModel, Long> {
    Page<CircuitoModel> findByEstaActivoTrue(Pageable paginacion);
}
