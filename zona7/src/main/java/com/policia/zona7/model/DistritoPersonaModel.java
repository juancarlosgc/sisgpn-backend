package com.policia.zona7.model;


import com.policia.zona7.dto.asignaciones.DatosActualizarDistritoPersonaDto;
import com.policia.zona7.dto.persona.DatosActualizarPersonaDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name="DistritoPersona")
@Table(name="distritospersonas")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of="idDistritoPersona")
public class DistritoPersonaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistritoPersona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_persona")
    private PersonaModel persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_subcircuito")
    private SubcircuitoModel subcircuito;

    @Temporal(TemporalType.DATE)
    private Date fecha;
    //private LocalDateTime fecha = LocalDateTime.now();

    public void actualizarDistritoPersona(DatosActualizarDistritoPersonaDto datosActualizarDistritoPersonaDto){
        this.persona.setIdPersona(datosActualizarDistritoPersonaDto.idPersona());
        this.subcircuito.setIdSubcircuito(datosActualizarDistritoPersonaDto.idSubcircuito());
        this.fecha= datosActualizarDistritoPersonaDto.fecha();
    }

    public DistritoPersonaModel(Long idDistritoPersona, PersonaModel persona, SubcircuitoModel subcircuito, Date fecha) {
        this.idDistritoPersona = idDistritoPersona;
        this.persona = persona;
        this.subcircuito = subcircuito;
        this.fecha = fecha;
    }

    /*public void desactivarDistritoModel() {
        this.estaActivo=false;
    }*/
}
