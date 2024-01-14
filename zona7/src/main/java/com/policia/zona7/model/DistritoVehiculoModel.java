package com.policia.zona7.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name="DistritoVehiculo")
@Table(name="distritosvehiculos")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of="idDistritoVehiculo")
public class DistritoVehiculoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistritoVehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_vehiculo")
    private VehiculoModel vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_subcircuito")
    private SubcircuitoModel subcircuito;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    public DistritoVehiculoModel(Long idDistritoVehiculo, VehiculoModel vehiculo, SubcircuitoModel subcircuito, Date fecha) {
        this.idDistritoVehiculo = idDistritoVehiculo;
        this.vehiculo = vehiculo;
        this.subcircuito = subcircuito;
        this.fecha = fecha;
    }
}
