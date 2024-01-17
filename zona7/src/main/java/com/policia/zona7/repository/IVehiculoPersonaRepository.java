package com.policia.zona7.repository;

import com.policia.zona7.model.PersonaModel;
import com.policia.zona7.model.VehiculoPersonaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IVehiculoPersonaRepository extends JpaRepository<VehiculoPersonaModel, Long> {

    // Object findByIdPersona(Long aLong);


    //Map<Object, Object> findByContador(Integer contador);

    //List<VehiculoPersonaModel> findByIdVehiculo(String nombre);

  /*  @Query("""
            select m from Vehiculo m 
            where m.estaActivo=1 and
            
            """)
    PersonaModel verificarPersonaAsignadaVehiculo(PersonaModel persona);
*/

    @Query("SELECT u FROM VehiculoPersona u WHERE u.persona.idPersona = 1")
    List<VehiculoPersonaModel> findByIdPersona(Long id);

}
