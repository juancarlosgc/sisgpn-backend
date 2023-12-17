package com.policia.zona7.dto.persona;

import com.policia.zona7.model.RangoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.StandardException;

import java.io.Serializable;


public record  DatosRegistroPersonaDto(
        @NotBlank
        @Pattern(regexp = "\\d{10}")
        String cedula,
        @NotBlank
        String apellidos,
        @NotBlank
        String nombres,
        @NotBlank
        String fechaNacimiento,
        @NotBlank
        String tipoSangre,
        @NotBlank
        String ciudadNacimiento,
        @NotBlank
        String telefono,
        @NotNull
        RangoEnum rango
) {


}
