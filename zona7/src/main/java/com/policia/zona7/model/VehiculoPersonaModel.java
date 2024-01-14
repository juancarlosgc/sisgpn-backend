package com.policia.zona7.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name="VehiculoPersona")
@Table(name="vehiculospersonas")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of="idVehiculoPersona")
public class VehiculoPersonaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculoPersona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_vehiculo")
    private VehiculoModel vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_persona")
    private PersonaModel persona;

    @Temporal(TemporalType.DATE)
    private Date fecha;



    public VehiculoPersonaModel(Long idVehiculoPersona, VehiculoModel vehiculo, PersonaModel persona, Date fecha) {
        this.idVehiculoPersona = idVehiculoPersona;
        this.vehiculo = vehiculo;
        this.persona = persona;
        this.fecha = fecha;
    }
}
