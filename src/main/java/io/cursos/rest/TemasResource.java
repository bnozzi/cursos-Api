package io.cursos.rest;

import io.cursos.model.TemasDTO;
import io.cursos.service.TemasService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/temass", produces = MediaType.APPLICATION_JSON_VALUE)
public class TemasResource {

    @Autowired
    private TemasService temasService;

    public TemasResource(TemasService temasService) {
        this.temasService = temasService;
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<List<TemasDTO>> temasByCurso(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(temasService.findAllByCursosId(id));
    }

    @GetMapping
    public ResponseEntity<List<TemasDTO>> getAllTemass() {
        return ResponseEntity.ok(temasService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemasDTO> getTemas(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(temasService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createTemas(@RequestBody @Valid final TemasDTO temasDTO) {
        final Long createdId = temasService.create(temasDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateTemas(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final TemasDTO temasDTO) {
        temasService.update(id, temasDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteTemas(@PathVariable(name = "id") final Long id) {
        temasService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
