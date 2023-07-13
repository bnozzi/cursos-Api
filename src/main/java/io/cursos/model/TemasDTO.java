package io.cursos.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TemasDTO {

    private Long id;

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    private Long cursos;

}
