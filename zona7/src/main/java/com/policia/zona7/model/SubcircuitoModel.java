package com.policia.zona7.model;


import com.policia.zona7.dto.subcircuito.DatosActualizarSubcircuitoDto;
import com.policia.zona7.dto.subcircuito.DatosRegistroSubcircuitoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name="Subcircuito")
@Table(name="subcircuitos")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of="idSubcircuito")
public class SubcircuitoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubcircuito;
    private String codigoSubcircuito;
    private String nombreSubcircuito;
    private Boolean estaActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCircuito")  //OPCIONAL
    private CircuitoModel circuito;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subcircuito", cascade = CascadeType.ALL)
    private List<PersonaModel> listaPersonas;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subcircuito", cascade = CascadeType.ALL)
    private List<VehiculoModel> listaVehiculos;




    public SubcircuitoModel(){
        this.listaPersonas=new ArrayList<>();
        this.listaVehiculos=new ArrayList<>();
    }

    public SubcircuitoModel(DatosRegistroSubcircuitoDto datosRegistroSubcircuitoDto) {
        this.codigoSubcircuito = datosRegistroSubcircuitoDto.codigoSubcircuito();
        this.nombreSubcircuito = datosRegistroSubcircuitoDto.nombreSubcircuito();
        this.estaActivo = true;
    }

    public void actualizarDatosSubcircuito(DatosActualizarSubcircuitoDto datosActualizarSubcircuitoDto) {
        if (datosActualizarSubcircuitoDto.codigoSubcircuito() != null) {
            this.codigoSubcircuito = datosActualizarSubcircuitoDto.codigoSubcircuito();
        }
        if (datosActualizarSubcircuitoDto.nombreSubcircuito() != null) {
            this.nombreSubcircuito = datosActualizarSubcircuitoDto.nombreSubcircuito();
        }
    }

    public void desactivarSubcircuito() {
        this.estaActivo=false;
    }

}
