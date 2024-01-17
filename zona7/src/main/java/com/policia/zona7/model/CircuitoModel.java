package com.policia.zona7.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.policia.zona7.dto.circuito.DatosActualizarCircuitoDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name="Circuito")
@Table(name="circuitos")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of="idCircuito")
public class CircuitoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCircuito;
    private String codigoCircuito;
    private String nombreCircuito;
    private Boolean estaActivo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_distrito")  //OPCIONAL
    @JsonIgnoreProperties(value={"circuitos","hibernateLazyInitializer","handler"},allowSetters = true)
    private DistritoModel distrito;

    /*@JsonIgnoreProperties(value={"circuitos","hibernateLazyInitializer","handler"},allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitos", cascade = CascadeType.ALL)
    private List<ReclamosModel> listaReclamos;*/



        public CircuitoModel(Long idCircuito, String codigoCircuito, String nombreCircuito, Boolean estaActivo, DistritoModel distrito) {
        this.idCircuito = idCircuito;
        this.codigoCircuito = codigoCircuito;
        this.nombreCircuito = nombreCircuito;
        this.estaActivo = estaActivo;
        this.distrito = distrito;
    }

    /* public void actualizarDatosCircuito(DatosActualizarCircuitoDto datos) {
        if (datos.codigoCircuito() != null) {
            this.codigoCircuito = datos.codigoCircuito();
        }
        if (datos.nombreCircuito() != null) {
            this.nombreCircuito = datos.nombreCircuito();
        }
    }*/

    public void desactivarCircuito() {
        this.estaActivo=false;
    }

}
