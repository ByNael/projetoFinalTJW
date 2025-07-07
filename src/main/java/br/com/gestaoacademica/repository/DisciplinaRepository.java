package br.com.gestaoacademica.repository;

import br.com.gestaoacademica.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    java.util.List<Disciplina> findByNomeContainingIgnoreCase(String nome);
} 