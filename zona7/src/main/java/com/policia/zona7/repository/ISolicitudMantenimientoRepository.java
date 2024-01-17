package com.policia.zona7.repository;

import com.policia.zona7.model.SolicitudMantenimientoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISolicitudMantenimientoRepository extends JpaRepository<SolicitudMantenimientoModel, Long> {
}
