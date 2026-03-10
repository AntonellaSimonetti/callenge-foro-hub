package ar.antonella.forohub.models.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record TopicoDTO(

        @NotBlank(message = "Este campo no puede ser vacio")
        String titulo,

        @NotBlank(message = "Este campo no puede ser vacio")
        String mensaje,

        @NotBlank(message = "Este campo no puede ser vacio")
        String autor,

        @NotBlank(message = "Este campo no puede ser vacio")
        String curso,

        @Future
        LocalDateTime fechaCreacion

) {
}
