package br.com.gestaoacademica.repository;

import br.com.gestaoacademica.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
} 