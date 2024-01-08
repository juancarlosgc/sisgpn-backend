package com.policia.zona7.repository;

import com.policia.zona7.model.CircuitoModel;
import com.policia.zona7.model.DistritoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICircuitoRepository extends JpaRepository<CircuitoModel, Long> {


    Page<CircuitoModel> findByEstaActivoTrue(Pageable paginacion);


   @Query(value = "select * from circuitos " +
           "where esta_activo=true " +
           "order by id_circuito ", nativeQuery=true)
    Page<CircuitoModel> obtenerCircuitosConDistritos(Pageable paginacion);
}
