package com.policia.zona7.repository;

import com.policia.zona7.model.DistritoPersonaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistritoPersonaRepository extends JpaRepository<DistritoPersonaModel, Long> {
}
