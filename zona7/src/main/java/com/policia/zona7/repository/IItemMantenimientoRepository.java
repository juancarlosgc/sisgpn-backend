package com.policia.zona7.repository;

import com.policia.zona7.model.DistritoModel;
import com.policia.zona7.model.ItemMantenimientoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemMantenimientoRepository  extends JpaRepository<ItemMantenimientoModel, Long> {
    Page<ItemMantenimientoModel> findByEstaActivoTrue(Pageable paginacion);
}
