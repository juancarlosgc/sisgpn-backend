package com.policia.zona7.repository;

import com.policia.zona7.model.PersonaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonaRepository extends JpaRepository<PersonaModel, Long> {

    Page<PersonaModel> findByEstaActivoTrue(Pageable paginacion);

    public Optional<PersonaModel> findByApellidos(String apellidos);



}
