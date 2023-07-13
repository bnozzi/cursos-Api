package io.cursos.repos;

import io.cursos.domain.Temas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import io.cursos.domain.Cursos;





public interface TemasRepository extends JpaRepository<Temas, Long> {

    
    public List<Temas> findAllByCursosId(Long cursoId);


    
}
