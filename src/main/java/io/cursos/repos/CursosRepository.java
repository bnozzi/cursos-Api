package io.cursos.repos;

import io.cursos.domain.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;



public interface CursosRepository extends JpaRepository<Cursos, Long> {

    public List<Cursos> findAllByNombreContaining(String name);


}
