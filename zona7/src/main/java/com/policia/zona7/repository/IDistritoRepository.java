package com.policia.zona7.repository;

import com.policia.zona7.model.DistritoModel;
import com.policia.zona7.model.PersonaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDistritoRepository extends JpaRepository<DistritoModel, Long> {

    Page<DistritoModel> findByEstaActivoTrue(Pageable paginacion);

    //List<DistritoModel> findByNombreDistrito = null;
}
