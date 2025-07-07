package br.com.gestaoacademica.repository;

import br.com.gestaoacademica.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    java.util.List<Turma> findBySemestreContainingIgnoreCaseOrProfessor_NomeContainingIgnoreCase(String semestre, String professorNome);
} 