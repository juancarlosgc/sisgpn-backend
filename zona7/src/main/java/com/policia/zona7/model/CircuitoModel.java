package com.policia.zona7.model;

import com.policia.zona7.dto.circuito.DatosActualizarCircuitoDto;
import com.policia.zona7.dto.circuito.DatosRegistroCircuitoDto;
import com.policia.zona7.dto.distrito.DatosActualizarDistritoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name="Circuito")
@Table(name="circuitos")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of="idCircuito")
public class CircuitoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCircuito;
    private String codigoCircuito;
    private String nombreCircuito;
    private Boolean estaActivo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDistrito")  //OPCIONAL
    private DistritoModel distrito;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuito", cascade = CascadeType.ALL)
    private List<SubcircuitoModel> listaSubcircuitos;
    public CircuitoModel(){
        this.listaSubcircuitos=new ArrayList<>();
    }


    public CircuitoModel(DatosRegistroCircuitoDto datosRegistroCircuitoDto) {
        this.codigoCircuito = datosRegistroCircuitoDto.codigoCircuito();
        this.nombreCircuito = datosRegistroCircuitoDto.nombreCircuito();
        this.estaActivo = true;
    }

    public void actualizarDatosCircuito(DatosActualizarCircuitoDto datosActualizarCircuitoDto) {
        if (datosActualizarCircuitoDto.codigoCircuito() != null) {
            this.codigoCircuito = datosActualizarCircuitoDto.codigoCircuito();
        }
        if (datosActualizarCircuitoDto.nombreCircuito() != null) {
            this.nombreCircuito = datosActualizarCircuitoDto.nombreCircuito();
        }
    }

    public void desactivarCircuito() {
        this.estaActivo=false;
    }
}
