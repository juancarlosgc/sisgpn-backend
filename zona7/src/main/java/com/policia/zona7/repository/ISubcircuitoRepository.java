package com.policia.zona7.repository;

import com.policia.zona7.model.SubcircuitoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubcircuitoRepository extends JpaRepository<SubcircuitoModel, Long> {

    Page<SubcircuitoModel> findByEstaActivoTrue(Pageable paginacion);
}
