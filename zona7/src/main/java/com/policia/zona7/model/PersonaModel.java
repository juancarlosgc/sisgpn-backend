package com.policia.zona7.model;

import com.policia.zona7.dto.persona.DatosActualizarPersonaDto;
import com.policia.zona7.dto.persona.DatosRegistroPersonaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Persona")
@Table(name="personas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="idPersona")
public class PersonaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    private String cedula;

    private String apellidos;

    private String nombres;

    private String fechaNacimiento;

    private String tipoSangre;

    private String ciudadNacimiento;

    private String telefono;

    @Enumerated
    private RangoEnum rango;

    private Boolean estaActivo;

    public PersonaModel(DatosRegistroPersonaDto datosRegistroPersonaDto) {
        this.cedula=datosRegistroPersonaDto.cedula();
        this.nombres=datosRegistroPersonaDto.nombres();
        this.apellidos=datosRegistroPersonaDto.apellidos();
        this.fechaNacimiento=datosRegistroPersonaDto.fechaNacimiento();
        this.tipoSangre=datosRegistroPersonaDto.tipoSangre();
        this.ciudadNacimiento=datosRegistroPersonaDto.ciudadNacimiento();
        this.telefono=datosRegistroPersonaDto.telefono();
        this.rango=datosRegistroPersonaDto.rango();
        this.estaActivo=true;
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
