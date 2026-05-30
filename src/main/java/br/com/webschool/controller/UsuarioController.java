package br.com.webschool.controller;

import br.com.webschool.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import br.com.webschool.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Spring automaticamente injeta o UsuarioService aqui
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        if (!model.containsAttribute("usuario")) {
            model.addAttribute("usuario", new Usuario());
        }
        return "usuario/formularioUsuario";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuario/listaUsuarios";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario, RedirectAttributes attributes) {
        try {
            if (usuario.getIdUsuario() != null) {
                usuarioService.atualizar(usuario.getIdUsuario(), usuario);
                attributes.addFlashAttribute("message", "Usuário atualizado com sucesso!");
            } else {
                usuarioService.salvar(usuario);
                attributes.addFlashAttribute("message", "Usuário cadastrado com sucesso! Faça seu login.");
            }
            return "redirect:/login";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Erro ao salvar usuário: " + e.getMessage());
            attributes.addFlashAttribute("usuario", usuario);
            return "redirect:/usuarios/criar";
        }
    }
}



