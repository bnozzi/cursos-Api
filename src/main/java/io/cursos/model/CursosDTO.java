package io.cursos.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CursosDTO {

    private Long id;

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String tipoCurso;

    private LocalDate fechaFinalizacion;

}
