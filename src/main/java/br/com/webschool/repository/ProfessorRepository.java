package br.com.webschool.repository;

import br.com.webschool.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    Optional<Professor> findByCpfProfessor(String cpfProfessor);

    List<Professor> findByNomeProfessorContainingIgnoreCase(String nomeProfessor);

    List<Professor> findByCurso_IdCurso(Integer idCurso);
}