package br.com.webschool.repository;

import br.com.webschool.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

    List<Disciplina> findByNomeDisciplinaContainingIgnoreCase(String nomeDisciplina);

    List<Disciplina> findByCurso_IdCurso(Integer idCurso);

    List<Disciplina> findByProfessor_IdProfessor(Integer idProfessor);
}