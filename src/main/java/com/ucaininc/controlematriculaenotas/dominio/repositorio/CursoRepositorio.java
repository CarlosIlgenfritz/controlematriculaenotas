package com.ucaininc.controlematriculaenotas.dominio.repositorio;

import com.ucaininc.controlematriculaenotas.dominio.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long> {
}
