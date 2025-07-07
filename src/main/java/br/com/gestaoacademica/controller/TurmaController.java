package br.com.gestaoacademica.controller;

import br.com.gestaoacademica.model.Turma;
import br.com.gestaoacademica.repository.TurmaRepository;
import br.com.gestaoacademica.repository.DisciplinaRepository;
import br.com.gestaoacademica.repository.ProfessorRepository;
import br.com.gestaoacademica.dto.TurmaFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public String listarTurmas(Model model) {
        model.addAttribute("turmas", turmaRepository.findAll());
        return "turma-list";
    }

    @GetMapping("/nova")
    public String novaTurma(Model model) {
        model.addAttribute("turmaForm", new TurmaFormDTO());
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        model.addAttribute("professores", professorRepository.findAll());
        return "turma-form";
    }

    @PostMapping
    public String salvarTurma(@ModelAttribute TurmaFormDTO turmaForm) {
        Turma turma = new Turma();
        turma.setId(turmaForm.getId());
        turma.setSemestre(turmaForm.getSemestre());
        turma.setDisciplina(disciplinaRepository.findById(turmaForm.getDisciplina()).orElse(null));
        turma.setProfessor(professorRepository.findById(turmaForm.getProfessor()).orElse(null));
        turmaRepository.save(turma);
        return "redirect:/turmas";
    }

    @GetMapping("/editar/{id}")
    public String editarTurma(@PathVariable Long id, Model model) {
        Optional<Turma> turmaOpt = turmaRepository.findById(id);
        if (turmaOpt.isPresent()) {
            Turma turma = turmaOpt.get();
            TurmaFormDTO turmaForm = new TurmaFormDTO();
            turmaForm.setId(turma.getId());
            turmaForm.setSemestre(turma.getSemestre());
            turmaForm.setDisciplina(turma.getDisciplina() != null ? turma.getDisciplina().getId() : null);
            turmaForm.setProfessor(turma.getProfessor() != null ? turma.getProfessor().getId() : null);
            model.addAttribute("turmaForm", turmaForm);
            model.addAttribute("disciplinas", disciplinaRepository.findAll());
            model.addAttribute("professores", professorRepository.findAll());
            return "turma-form";
        } else {
            return "redirect:/turmas";
        }
    }

    @GetMapping("/remover/{id}")
    public String removerTurma(@PathVariable Long id) {
        turmaRepository.deleteById(id);
        return "redirect:/turmas";
    }
} 