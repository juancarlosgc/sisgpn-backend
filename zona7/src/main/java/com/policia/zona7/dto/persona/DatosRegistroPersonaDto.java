package com.policia.zona7.dto.persona;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.policia.zona7.model.RangoEnum;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.StandardException;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


public record  DatosRegistroPersonaDto(
        @NotBlank
        @Pattern(regexp = "\\d{10}")
        String cedula,
        @NotBlank
        String apellidos,
        @NotBlank
        String nombres,
        @NotNull
        Date fechaNacimiento,
        @NotBlank
        String tipoSangre,
        @NotBlank
        String ciudadNacimiento,
        @NotBlank
        String telefono,
        @NotNull
        RangoEnum rango,
        Long idVehiculo
) {


}
