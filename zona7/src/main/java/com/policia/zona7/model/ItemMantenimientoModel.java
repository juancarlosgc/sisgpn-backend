package com.policia.zona7.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.policia.zona7.dto.item.DatosActualizarItemDto;
import com.policia.zona7.dto.item.DatosRegistroItemDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Entity(name="Item")
@Table(name="items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="idItem")
public class ItemMantenimientoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    private String codigoItem;

    private String nombreItem;

    private String descripcionItem;

    private Boolean estaActivo;
    @JsonIgnoreProperties(value={"item","hibernateLazyInitializer","handler"},allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMantenimiento")  //OPCIONAL
    private MantenimientoModel mantenimiento;

    public ItemMantenimientoModel(Long idItem, String codigoItem, String nombreItem, String descripcionItem, MantenimientoModel mantenimiento) {
        this.idItem = idItem;
        this.codigoItem = codigoItem;
        this.nombreItem = nombreItem;
        this.descripcionItem = descripcionItem;
        this.mantenimiento = mantenimiento;
    }


    public void desactivarItem() {
        this.estaActivo=false;
    }
}
