package br.com.gestaoacademica.repository;

import br.com.gestaoacademica.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    java.util.List<Professor> findByNomeContainingIgnoreCase(String nome);
} 