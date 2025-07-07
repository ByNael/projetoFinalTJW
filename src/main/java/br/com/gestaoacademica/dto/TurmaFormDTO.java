package br.com.gestaoacademica.dto;

public class TurmaFormDTO {
    private Long id;
    private Long disciplina;
    private Long professor;
    private String semestre;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getDisciplina() { return disciplina; }
    public void setDisciplina(Long disciplina) { this.disciplina = disciplina; }
    public Long getProfessor() { return professor; }
    public void setProfessor(Long professor) { this.professor = professor; }
    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }
} 