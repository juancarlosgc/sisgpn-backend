package com.policia.zona7.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name="Reclamos")
@Table(name="reclamos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="idReclamo")
public class ReclamosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReclamo;
    @Enumerated
    private TipoIncidenteEnum tipoIncidente;
    private String detalle;
    private String contacto;
    private String apellidos;
    private String nombres;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @JsonIgnoreProperties(value={"reclamo","hibernateLazyInitializer","handler"},allowSetters = true)// aquiii
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subcircuito")  //OPCIONAL
    public SubcircuitoModel subcircuitos;

    @JsonIgnoreProperties(value={"reclamo","hibernateLazyInitializer","handler"},allowSetters = true)// aquiii
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_circuito")  //OPCIONAL
    public CircuitoModel circuitos;


    @PrePersist
    public void preGuardar(){
        this.fecha=new Date();
    }
}
