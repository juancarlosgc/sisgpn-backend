package com.policia.zona7.model;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_circuito")  //OPCIONAL
    private CircuitoModel circuito;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subcircuito", cascade = CascadeType.ALL)
    private List<PersonaModel> listaPersonas;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subcircuito", cascade = CascadeType.ALL)
    private List<VehiculoModel> listaVehiculos;

    public void desactivarSubcircuito() {
        this.estaActivo=false;
    }

}
