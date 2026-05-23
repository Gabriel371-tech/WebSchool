package br.com.webschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webschool.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{
    

}
