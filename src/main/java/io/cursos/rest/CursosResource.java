package io.cursos.rest;

import io.cursos.model.CursosDTO;
import io.cursos.service.CursosService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cursoss", produces = MediaType.APPLICATION_JSON_VALUE)
public class CursosResource {

    private final CursosService cursosService;

    public CursosResource(final CursosService cursosService) {
        this.cursosService = cursosService;
    }

    @GetMapping
    public ResponseEntity<List<CursosDTO>> getAllCursoss() {
        return ResponseEntity.ok(cursosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursosDTO> getCursos(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(cursosService.get(id));
    }

    @GetMapping("/curso")
    public ResponseEntity<List<CursosDTO>> getCursosJava(@RequestParam(value = "contiene") String name) {
        return ResponseEntity.ok(cursosService.findByContainingName(name));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCursos(@RequestBody @Valid final CursosDTO cursosDTO) {
        final Long createdId = cursosService.create(cursosDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCursos(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final CursosDTO cursosDTO) {
        cursosService.update(id, cursosDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCursos(@PathVariable(name = "id") final Long id) {
        cursosService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
