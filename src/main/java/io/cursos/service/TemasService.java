package io.cursos.service;

import io.cursos.domain.Cursos;
import io.cursos.domain.Temas;
import io.cursos.model.TemasDTO;
import io.cursos.repos.CursosRepository;
import io.cursos.repos.TemasRepository;
import io.cursos.util.NotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TemasService {
    
    @Autowired
    public TemasRepository temasRepository;

    @Autowired
    public CursosRepository cursosRepository;

    public TemasService(final TemasRepository temasRepository,
            final CursosRepository cursosRepository) {
        this.temasRepository = temasRepository;
        this.cursosRepository = cursosRepository;
    }

    public List<TemasDTO> findAllByCursosId(final Long cursoId) {
        List<Temas> temass = this.temasRepository.findAllByCursosId(cursoId);
        return temass.stream()
                .map(temas -> mapToDTO(temas, new TemasDTO()))
                .toList();
    }

    public List<TemasDTO> findAll() {
        final List<Temas> temass = temasRepository.findAll(Sort.by("id"));
        return temass.stream()
                .map(temas -> mapToDTO(temas, new TemasDTO()))
                .toList();
    }

    public TemasDTO get(final Long id) {
        return temasRepository.findById(id)
                .map(temas -> mapToDTO(temas, new TemasDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TemasDTO temasDTO) {
        final Temas temas = new Temas();
        mapToEntity(temasDTO, temas);
        return temasRepository.save(temas).getId();
    }

    public void update(final Long id, final TemasDTO temasDTO) {
        final Temas temas = temasRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(temasDTO, temas);
        temasRepository.save(temas);
    }

    public void delete(final Long id) {
        temasRepository.deleteById(id);
    }

    private TemasDTO mapToDTO(final Temas temas, final TemasDTO temasDTO) {
        temasDTO.setId(temas.getId());
        temasDTO.setNombre(temas.getNombre());
        temasDTO.setDescripcion(temas.getDescripcion());
        temasDTO.setCursos(temas.getCursos() == null ? null : temas.getCursos().getId());
        return temasDTO;
    }

    private Temas mapToEntity(final TemasDTO temasDTO, final Temas temas) {
        temas.setNombre(temasDTO.getNombre());
        temas.setDescripcion(temasDTO.getDescripcion());
        final Cursos cursos = temasDTO.getCursos() == null ? null
                : cursosRepository.findById(temasDTO.getCursos())
                        .orElseThrow(() -> new NotFoundException("cursos not found"));
        temas.setCursos(cursos);
        return temas;
    }

}
