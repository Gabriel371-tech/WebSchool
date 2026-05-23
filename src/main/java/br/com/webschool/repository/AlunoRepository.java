package br.com.webschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webschool.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
    

}
