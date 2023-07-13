package io.cursos.service;

import io.cursos.domain.Cursos;
import io.cursos.model.CursosDTO;
import io.cursos.repos.CursosRepository;
import io.cursos.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CursosService {

    private final CursosRepository cursosRepository;

    public CursosService(final CursosRepository cursosRepository) {
        this.cursosRepository = cursosRepository;
    }

    public List<CursosDTO> findByContainingName(String name) {
        final List<Cursos> cursoss = cursosRepository.findAllByNombreContaining(name);
        return cursoss.stream()
                .map(cursos -> mapToDTO(cursos, new CursosDTO()))
                .toList();

    }
    

    public List<CursosDTO> findAll() {
        final List<Cursos> cursoss = cursosRepository.findAll(Sort.by("id"));
        return cursoss.stream()
                .map(cursos -> mapToDTO(cursos, new CursosDTO()))
                .toList();
    }

    public CursosDTO get(final Long id) {
        return cursosRepository.findById(id)
                .map(cursos -> mapToDTO(cursos, new CursosDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CursosDTO cursosDTO) {
        final Cursos cursos = new Cursos();
        mapToEntity(cursosDTO, cursos);
        return cursosRepository.save(cursos).getId();
    }

    public void update(final Long id, final CursosDTO cursosDTO) {
        final Cursos cursos = cursosRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(cursosDTO, cursos);
        cursosRepository.save(cursos);
    }

    public void delete(final Long id) {
        cursosRepository.deleteById(id);
    }

    private CursosDTO mapToDTO(final Cursos cursos, final CursosDTO cursosDTO) {
        cursosDTO.setId(cursos.getId());
        cursosDTO.setNombre(cursos.getNombre());
        cursosDTO.setTipoCurso(cursos.getTipoCurso());
        cursosDTO.setFechaFinalizacion(cursos.getFechaFinalizacion());
        return cursosDTO;
    }

    private Cursos mapToEntity(final CursosDTO cursosDTO, final Cursos cursos) {
        cursos.setNombre(cursosDTO.getNombre());
        cursos.setTipoCurso(cursosDTO.getTipoCurso());
        cursos.setFechaFinalizacion(cursosDTO.getFechaFinalizacion());
        return cursos;
    }

}
