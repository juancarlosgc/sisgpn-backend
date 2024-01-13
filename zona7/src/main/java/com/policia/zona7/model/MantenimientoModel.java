package com.policia.zona7.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.policia.zona7.dto.mantenimiento.DatosActualizarMantenimientoDto;
import com.policia.zona7.dto.mantenimiento.DatosRegistroMantenimientoDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name="Mantenimiento")
@Table(name="mantenimientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="idMantenimiento")
public class MantenimientoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMantenimiento;
    private String codigoMantenimiento;
    private  String nombreMantenimiento;
    private Double costoMantenimiento;
    private Boolean estaActivo;


    @JsonIgnoreProperties(value={"mantenimiento","hibernateLazyInitializer","handler"},allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mantenimiento", cascade = CascadeType.ALL)
    private List<ItemMantenimientoModel> listaItems;

    public MantenimientoModel(DatosRegistroMantenimientoDto datosRegistroMantenimientoDto) {
        this.codigoMantenimiento= datosRegistroMantenimientoDto.codigoMantenimiento();
        this.nombreMantenimiento= datosRegistroMantenimientoDto.nombreMantenimiento();
        this.costoMantenimiento= datosRegistroMantenimientoDto.costoMantenimiento();
        this.estaActivo=true;
    }

    public void actualizarDatosMantenimiento(DatosActualizarMantenimientoDto datosActualizarMantenimientoDto){
        if (datosActualizarMantenimientoDto.codigoMantenimiento()!= null){
            this.codigoMantenimiento= datosActualizarMantenimientoDto.codigoMantenimiento();
        }

        if (datosActualizarMantenimientoDto.nombreMantenimiento()!= null){
            this.nombreMantenimiento= datosActualizarMantenimientoDto.nombreMantenimiento();
        }

        if (datosActualizarMantenimientoDto.costoMantenimiento()!= null){
            this.costoMantenimiento= datosActualizarMantenimientoDto.costoMantenimiento();
        }
    }

    public void desactivarMantenimiento() {
        this.estaActivo=false;
    }
}
