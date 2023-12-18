package com.policia.zona7.model;

import com.policia.zona7.dto.persona.DatosActualizarPersonaDto;
import com.policia.zona7.dto.persona.DatosRegistroPersonaDto;
import com.policia.zona7.dto.vehiculo.DatosActualizarVehiculoDto;
import com.policia.zona7.dto.vehiculo.DatosRegistroVehiculoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name="Vehiculo")
@Table(name="vehiculos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="idVehiculo")
public class VehiculoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;
    private String placa;
    private String chasis;
    private String marca;
    private String modelo;
    private String motor;
    private Double kilometraje;
    private Double cilindraje;
    private Double capacidadCarga;
    private Integer cantidadPasajeros;
    @Enumerated
    private TipoVehiculoEnum tipoVehiculo;
    private String observaciones;
    private Boolean estaActivo;

    @OneToMany (mappedBy = "vehiculo")
    private List<PersonaModel> listaPersonas;

    public void desactivarVehiculo() {
        this.estaActivo=false;
    }

    public VehiculoModel(DatosRegistroVehiculoDto datosRegistroVehiculoDto) {
        this.placa=datosRegistroVehiculoDto.placa();
        this.chasis= datosRegistroVehiculoDto.chasis();
        this.marca= datosRegistroVehiculoDto.marca();
        this.modelo= datosRegistroVehiculoDto.modelo();
        this.motor= datosRegistroVehiculoDto.motor();
        this.kilometraje= datosRegistroVehiculoDto.kilometraje();
        this.cilindraje= datosRegistroVehiculoDto.cilindraje();
        this.capacidadCarga= datosRegistroVehiculoDto.capacidadCarga();
        this.cantidadPasajeros=datosRegistroVehiculoDto.capacidadPasajeros();
        this.tipoVehiculo=datosRegistroVehiculoDto.tipoVehiculo();
        this.observaciones=datosRegistroVehiculoDto.observaciones();
        this.estaActivo=true;
    }

    public void actualizarDatosVehiculo(DatosActualizarVehiculoDto datosActualizarVehiculoDto){
        if (datosActualizarVehiculoDto.placa() != null){
            this.placa=datosActualizarVehiculoDto.placa();
        }
        if (datosActualizarVehiculoDto.chasis() != null){
            this.chasis=datosActualizarVehiculoDto.chasis();
        }
        if (datosActualizarVehiculoDto.marca() != null){
            this.marca=datosActualizarVehiculoDto.marca();
        }
        if (datosActualizarVehiculoDto.modelo() != null){
            this.modelo=datosActualizarVehiculoDto.modelo();
        }
        if (datosActualizarVehiculoDto.motor() != null){
            this.motor=datosActualizarVehiculoDto.motor();
        }
        if (datosActualizarVehiculoDto.kilometraje() != null){
            this.kilometraje=datosActualizarVehiculoDto.kilometraje();
        }
        if (datosActualizarVehiculoDto.cilindraje() != null){
            this.cilindraje=datosActualizarVehiculoDto.cilindraje();
        }
        if (datosActualizarVehiculoDto.capacidadCarga() != null){
            this.capacidadCarga=datosActualizarVehiculoDto.capacidadCarga();
        }
        if (datosActualizarVehiculoDto.capacidadPasajeros() != null){
            this.cantidadPasajeros=datosActualizarVehiculoDto.capacidadPasajeros();
        }
        if (datosActualizarVehiculoDto.observaciones() != null){
            this.observaciones=datosActualizarVehiculoDto.observaciones();
        }
        if (datosActualizarVehiculoDto.tipoVehiculo() != null){
            this.tipoVehiculo=datosActualizarVehiculoDto.tipoVehiculo();
        }

    }
}
