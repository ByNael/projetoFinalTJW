package br.com.gestaoacademica.dto;

import java.time.LocalDate;

public class MatriculaFormDTO {
    private Long id;
    private Long aluno;
    private Long turma;
    private LocalDate dataMatricula;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAluno() { return aluno; }
    public void setAluno(Long aluno) { this.aluno = aluno; }
    public Long getTurma() { return turma; }
    public void setTurma(Long turma) { this.turma = turma; }
    public LocalDate getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
} 