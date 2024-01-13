package com.policia.zona7.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.policia.zona7.dto.subcircuito.DatosActualizarSubcircuitoDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name="Subcircuito")
@Table(name="subcircuitos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="idSubcircuito")
public class SubcircuitoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubcircuito;
    private String codigoSubcircuito;
    private String nombreSubcircuito;
    private Boolean estaActivo;

    @JsonIgnoreProperties(value={"subcircuito","hibernateLazyInitializer","handler"},allowSetters = true)// aquiii
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_circuito")  //OPCIONAL
    private CircuitoModel circuito;

    @JsonIgnoreProperties(value={"subcircuito","hibernateLazyInitializer","handler"},allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subcircuito", cascade = CascadeType.ALL)
    private List<PersonaModel> listaPersonas;

    @JsonIgnoreProperties(value={"subcircuito","hibernateLazyInitializer","handler"},allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subcircuito", cascade = CascadeType.ALL)
    private List<VehiculoModel> listaVehiculos;

    public void desactivarSubcircuito() {
        this.estaActivo=false;
    }


    public SubcircuitoModel(Long idSubcircuito, String codigoSubcircuito, String nombreSubcircuito, Boolean estaActivo, CircuitoModel circuito) {
        this.idSubcircuito = idSubcircuito;
        this.codigoSubcircuito = codigoSubcircuito;
        this.nombreSubcircuito = nombreSubcircuito;
        this.estaActivo = estaActivo;
        this.circuito = circuito;
    }

    public SubcircuitoModel(Long idSubcircuito, String codigoSubcircuito, String nombreSubcircuito, Boolean estaActivo) {
        this.idSubcircuito = idSubcircuito;
        this.codigoSubcircuito = codigoSubcircuito;
        this.nombreSubcircuito = nombreSubcircuito;
        this.estaActivo = estaActivo;
    }
}
