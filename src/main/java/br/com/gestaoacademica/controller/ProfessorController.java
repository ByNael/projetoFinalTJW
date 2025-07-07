package br.com.gestaoacademica.controller;

import br.com.gestaoacademica.model.Professor;
import br.com.gestaoacademica.repository.ProfessorRepository;
import br.com.gestaoacademica.dto.ProfessorDTO;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/professores")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public String listarProfessores(@RequestParam(value = "busca", required = false) String busca, Model model, @ModelAttribute("mensagem") String mensagem) {
        if (busca != null && !busca.isEmpty()) {
            model.addAttribute("professores", professorRepository.findByNomeContainingIgnoreCase(busca));
            model.addAttribute("busca", busca);
        } else {
            model.addAttribute("professores", professorRepository.findAll());
        }
        model.addAttribute("mensagem", mensagem);
        return "professor-list";
    }

    @GetMapping("/novo")
    public String novoProfessor(Model model) {
        model.addAttribute("professor", new ProfessorDTO());
        return "professor-form";
    }

    @PostMapping
    public String salvarProfessor(@Valid @ModelAttribute("professor") ProfessorDTO professorDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "professor-form";
        }
        Professor professor = new Professor();
        professor.setId(professorDTO.getId());
        professor.setNome(professorDTO.getNome());
        professor.setEmail(professorDTO.getEmail());
        professor.setAreaAtuacao(professorDTO.getAreaAtuacao());
        professorRepository.save(professor);
        if (professorDTO.getId() == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Professor cadastrado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Professor atualizado com sucesso!");
        }
        return "redirect:/professores";
    }

    @GetMapping("/editar/{id}")
    public String editarProfessor(@PathVariable Long id, Model model) {
        Optional<Professor> professor = professorRepository.findById(id);
        if (professor.isPresent()) {
            ProfessorDTO professorDTO = new ProfessorDTO();
            professorDTO.setId(professor.get().getId());
            professorDTO.setNome(professor.get().getNome());
            professorDTO.setEmail(professor.get().getEmail());
            professorDTO.setAreaAtuacao(professor.get().getAreaAtuacao());
            model.addAttribute("professor", professorDTO);
            return "professor-form";
        } else {
            return "redirect:/professores";
        }
    }

    @GetMapping("/remover/{id}")
    public String removerProfessor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        professorRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Professor removido com sucesso!");
        return "redirect:/professores";
    }
} 