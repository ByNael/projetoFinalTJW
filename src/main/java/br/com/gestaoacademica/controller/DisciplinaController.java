package br.com.gestaoacademica.controller;

import br.com.gestaoacademica.dto.DisciplinaDTO;
import br.com.gestaoacademica.model.Disciplina;
import br.com.gestaoacademica.repository.DisciplinaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public String listarDisciplinas(@RequestParam(value = "busca", required = false) String busca, Model model, @ModelAttribute("mensagem") String mensagem) {
        if (busca != null && !busca.isEmpty()) {
            model.addAttribute("disciplinas", disciplinaRepository.findByNomeContainingIgnoreCase(busca));
            model.addAttribute("busca", busca);
        } else {
            model.addAttribute("disciplinas", disciplinaRepository.findAll());
        }
        model.addAttribute("mensagem", mensagem);
        return "disciplina-list";
    }

    @GetMapping("/novo")
    public String novaDisciplina(Model model) {
        model.addAttribute("disciplina", new DisciplinaDTO());
        return "disciplina-form";
    }

    @PostMapping
    public String salvarDisciplina(@Valid @ModelAttribute("disciplina") DisciplinaDTO disciplinaDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "disciplina-form";
        }
        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaDTO.getId());
        disciplina.setNome(disciplinaDTO.getNome());
        disciplina.setCodigo(disciplinaDTO.getCodigo());
        disciplina.setEmenta(disciplinaDTO.getEmenta());
        disciplina.setCargaHoraria(disciplinaDTO.getCargaHoraria());
        // Se houver outros campos no DTO, adicionar aqui
        disciplinaRepository.save(disciplina);
        if (disciplinaDTO.getId() == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Disciplina cadastrada com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Disciplina atualizada com sucesso!");
        }
        return "redirect:/disciplinas";
    }

    @GetMapping("/editar/{id}")
    public String editarDisciplina(@PathVariable Long id, Model model) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        if (disciplina.isPresent()) {
            DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
            disciplinaDTO.setId(disciplina.get().getId());
            disciplinaDTO.setNome(disciplina.get().getNome());
            disciplinaDTO.setCodigo(disciplina.get().getCodigo());
            disciplinaDTO.setEmenta(disciplina.get().getEmenta());
            disciplinaDTO.setCargaHoraria(disciplina.get().getCargaHoraria());
            // Se houver outros campos no DTO, adicionar aqui
            model.addAttribute("disciplina", disciplinaDTO);
            return "disciplina-form";
        } else {
            return "redirect:/disciplinas";
        }
    }

    @GetMapping("/remover/{id}")
    public String removerDisciplina(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        disciplinaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Disciplina removida com sucesso!");
        return "redirect:/disciplinas";
    }
} 