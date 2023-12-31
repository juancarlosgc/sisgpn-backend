package com.policia.zona7.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.policia.zona7.dto.distrito.DatosActualizarDistritoDto;
import com.policia.zona7.dto.distrito.DatosRegistroDistritoDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="Distrito")
@Table(name="distritos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="idDistrito")
public class DistritoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistrito;
    private String codigoDistrito;
    private String nombreDistrito;
    private String parroquia;
    private Boolean estaActivo;

    @JsonIgnoreProperties(value={"distrito","hibernateLazyInitializer","handler"},allowSetters = true)
    //@JsonIgnoreProperties({"distrito"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "distrito", cascade = CascadeType.ALL)
    private List<CircuitoModel> listaCircuitos;

  /*  public DistritoModel(){
        this.listaCircuitos=new ArrayList<>();
    }*/

    public DistritoModel(DatosRegistroDistritoDto datosRegistroDistritoDto) {
        this.codigoDistrito=datosRegistroDistritoDto.codigoDistrito();
        this.nombreDistrito=datosRegistroDistritoDto.nombreDistrito();
        this.parroquia=datosRegistroDistritoDto.parroquia();
        this.estaActivo=true;
    }

    public void actualizarDatosDistrito(DatosActualizarDistritoDto datosActualizarDistritoDto){
        if (datosActualizarDistritoDto.codigoDistrito() != null){
            this.codigoDistrito= datosActualizarDistritoDto.codigoDistrito();
        }
         if(datosActualizarDistritoDto.nombreDistrito()!= null){
             this.nombreDistrito= datosActualizarDistritoDto.nombreDistrito();
         }
         if (datosActualizarDistritoDto.parroquia()!= null){
             this.parroquia= datosActualizarDistritoDto.parroquia();
         }
    }

    public void desactivarDistrito() {
        this.estaActivo=false;
    }
}
