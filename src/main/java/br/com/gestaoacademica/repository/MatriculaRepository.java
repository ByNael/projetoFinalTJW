package br.com.gestaoacademica.repository;

import br.com.gestaoacademica.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    java.util.List<Matricula> findByAluno_Id(Long alunoId);
    java.util.List<Matricula> findByTurma_Id(Long turmaId);
} 