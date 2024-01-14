package com.policia.zona7.repository;

import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.model.VehiculoPersonaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IVehiculoPersonaRepository extends JpaRepository<VehiculoPersonaModel, Long> {
    //Map<Object, Object> findByContador(Integer contador);

    //List<VehiculoPersonaModel> findByIdVehiculo(String nombre);


}
