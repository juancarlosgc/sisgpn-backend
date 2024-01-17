package com.policia.zona7.model;


import jakarta.persistence.*;
import lombok.*;



import java.util.Date;

@Entity(name="Solicitu")
@Table(name="solicitud")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="idDistrito")
public class SolicitudMantenimientoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;
    private Date fechaSolicitada;
    private Date horaSolicitada;
    private Double kilometrajeActual;
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_persona")
    private PersonaModel persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_vehiculo")
    private VehiculoModel vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_subcircuito")
    private SubcircuitoModel subcircuito;


}
