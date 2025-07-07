package br.com.gestaoacademica.controller;

import br.com.gestaoacademica.model.Matricula;
import br.com.gestaoacademica.repository.MatriculaRepository;
import br.com.gestaoacademica.repository.AlunoRepository;
import br.com.gestaoacademica.repository.TurmaRepository;
import br.com.gestaoacademica.dto.MatriculaFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public String listarMatriculas(Model model) {
        model.addAttribute("matriculas", matriculaRepository.findAll());
        return "matricula-list";
    }

    @GetMapping("/nova")
    public String novaMatricula(Model model) {
        model.addAttribute("matriculaForm", new MatriculaFormDTO());
        model.addAttribute("alunos", alunoRepository.findAll());
        model.addAttribute("turmas", turmaRepository.findAll());
        return "matricula-form";
    }

    @PostMapping
    public String salvarMatricula(@ModelAttribute MatriculaFormDTO matriculaForm) {
        Matricula matricula = new Matricula();
        matricula.setId(matriculaForm.getId());
        matricula.setDataMatricula(matriculaForm.getDataMatricula());
        matricula.setStatus(matriculaForm.getStatus());
        matricula.setAluno(alunoRepository.findById(matriculaForm.getAluno()).orElse(null));
        matricula.setTurma(turmaRepository.findById(matriculaForm.getTurma()).orElse(null));
        matriculaRepository.save(matricula);
        return "redirect:/matriculas";
    }

    @GetMapping("/editar/{id}")
    public String editarMatricula(@PathVariable Long id, Model model) {
        Optional<Matricula> matriculaOpt = matriculaRepository.findById(id);
        if (matriculaOpt.isPresent()) {
            Matricula matricula = matriculaOpt.get();
            MatriculaFormDTO matriculaForm = new MatriculaFormDTO();
            matriculaForm.setId(matricula.getId());
            matriculaForm.setDataMatricula(matricula.getDataMatricula());
            matriculaForm.setStatus(matricula.getStatus());
            matriculaForm.setAluno(matricula.getAluno() != null ? matricula.getAluno().getId() : null);
            matriculaForm.setTurma(matricula.getTurma() != null ? matricula.getTurma().getId() : null);
            model.addAttribute("matriculaForm", matriculaForm);
            model.addAttribute("alunos", alunoRepository.findAll());
            model.addAttribute("turmas", turmaRepository.findAll());
            return "matricula-form";
        } else {
            return "redirect:/matriculas";
        }
    }

    @GetMapping("/remover/{id}")
    public String removerMatricula(@PathVariable Long id) {
        matriculaRepository.deleteById(id);
        return "redirect:/matriculas";
    }
} 