package br.com.gestaoacademica.controller;

import br.com.gestaoacademica.model.Aluno;
import br.com.gestaoacademica.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import br.com.gestaoacademica.dto.AlunoDTO;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public String listarAlunos(@RequestParam(value = "busca", required = false) String busca, Model model, @ModelAttribute("mensagem") String mensagem) {
        if (busca != null && !busca.isEmpty()) {
            model.addAttribute("alunos", alunoRepository.findByNomeContainingIgnoreCase(busca));
            model.addAttribute("busca", busca);
        } else {
            model.addAttribute("alunos", alunoRepository.findAll());
        }
        model.addAttribute("mensagem", mensagem);
        return "aluno-list";
    }

    @GetMapping("/novo")
    public String novoAluno(Model model) {
        model.addAttribute("aluno", new AlunoDTO());
        return "aluno-form";
    }

    @PostMapping
    public String salvarAluno(@Valid @ModelAttribute("aluno") AlunoDTO alunoDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "aluno-form";
        }
        Aluno aluno = new Aluno();
        aluno.setId(alunoDTO.getId());
        aluno.setNome(alunoDTO.getNome());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setDataNascimento(alunoDTO.getDataNascimento());
        alunoRepository.save(aluno);
        if (alunoDTO.getId() == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Aluno cadastrado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Aluno atualizado com sucesso!");
        }
        return "redirect:/alunos";
    }

    @GetMapping("/editar/{id}")
    public String editarAluno(@PathVariable Long id, Model model) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            AlunoDTO alunoDTO = new AlunoDTO();
            alunoDTO.setId(aluno.get().getId());
            alunoDTO.setNome(aluno.get().getNome());
            alunoDTO.setEmail(aluno.get().getEmail());
            alunoDTO.setDataNascimento(aluno.get().getDataNascimento());
            model.addAttribute("aluno", alunoDTO);
            return "aluno-form";
        } else {
            return "redirect:/alunos";
        }
    }

    @GetMapping("/remover/{id}")
    public String removerAluno(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        alunoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Aluno removido com sucesso!");
        return "redirect:/alunos";
    }
} 