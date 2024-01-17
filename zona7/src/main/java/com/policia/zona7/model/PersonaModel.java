package com.policia.zona7.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.policia.zona7.dto.persona.DatosActualizarPersonaDto;
import com.policia.zona7.dto.persona.DatosRegistroPersonaDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name="Persona")
@Table(name="personas")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of="idPersona")

public class PersonaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Pattern(regexp = "\\d{10}")
    private Long idPersona;
    private String cedula;
    private String apellidos;
    private String nombres;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private String tipoSangre;
    private String ciudadNacimiento;
    private String telefono;
    @Enumerated
    private RangoEnum rango;
    private Boolean estaActivo;

    @JsonIgnoreProperties(value={"persona","hibernateLazyInitializer","handler"},allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo")  //OPCIONAL
    private VehiculoModel vehiculo;

    @JsonIgnoreProperties(value={"persona","hibernateLazyInitializer","handler"},allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subcircuito")  //OPCIONAL
    private SubcircuitoModel subcircuito;


  /* @ManyToOne
   @JoinColumn (name="idVehiculo")
   VehiculoModel vehiculo;*/



    public PersonaModel(DatosRegistroPersonaDto datosRegistroPersonaDto) {
        this.cedula=datosRegistroPersonaDto.cedula();
        this.nombres=datosRegistroPersonaDto.nombres().toUpperCase();
        this.apellidos=datosRegistroPersonaDto.apellidos().toUpperCase();
        this.fechaNacimiento=datosRegistroPersonaDto.fechaNacimiento();
        this.tipoSangre=datosRegistroPersonaDto.tipoSangre().toUpperCase();
        this.ciudadNacimiento=datosRegistroPersonaDto.ciudadNacimiento().toUpperCase();
        this.telefono=datosRegistroPersonaDto.telefono();
        this.rango=datosRegistroPersonaDto.rango();
        this.estaActivo=true;
    }

    public PersonaModel(Long idPersona, String cedula, String apellidos, String nombres, Date fechaNacimiento, String tipoSangre, String ciudadNacimiento, String telefono, RangoEnum rango, Boolean estaActivo, VehiculoModel vehiculo, SubcircuitoModel subcircuito) {
        this.idPersona = idPersona;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
        this.ciudadNacimiento = ciudadNacimiento;
        this.telefono = telefono;
        this.rango = rango;
        this.estaActivo = estaActivo;
        this.vehiculo = vehiculo;
        this.subcircuito = subcircuito;
    }

    public void desactivarPersona() {
        this.estaActivo=false;
    }

    public void actualizarDatosPersona(DatosActualizarPersonaDto datosActualizarPersonaDto){
        if (datosActualizarPersonaDto.cedula()!= null){
            this.cedula=datosActualizarPersonaDto.cedula();
        }
        if (datosActualizarPersonaDto.nombres()!= null){
            this.nombres=datosActualizarPersonaDto.nombres();
        }
        if (datosActualizarPersonaDto.apellidos()!= null){
            this.apellidos=datosActualizarPersonaDto.apellidos();
        }
        if (datosActualizarPersonaDto.fechaNacimiento()!= null) {
            this.fechaNacimiento = datosActualizarPersonaDto.fechaNacimiento();
        }
        if (datosActualizarPersonaDto.tipoSangre()!= null){
            this.tipoSangre=datosActualizarPersonaDto.tipoSangre();
        }
        if (datosActualizarPersonaDto.telefono()!= null){
            this.telefono=datosActualizarPersonaDto.telefono();
        }
        if (datosActualizarPersonaDto.rango()!= null){
            this.rango=datosActualizarPersonaDto.rango();
        }
}

}
