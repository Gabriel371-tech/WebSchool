package br.com.webschool.controller;

import br.com.webschool.entity.Produto;
import br.com.webschool.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("produtos", produtoService.findAll());
        return "produto/listaProdutos";
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "produto/formularioProduto";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto) {
        if (produto.getIdProduto() != null) {
            produtoService.update(produto.getIdProduto(), produto);
        } else {
            produtoService.save(produto);
        }
        return "redirect:/produtos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Produto produto = produtoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        model.addAttribute("produto", produto);
        return "produto/formularioProduto";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id, RedirectAttributes attributes) {
        try {
            produtoService.deleteById(id);
            attributes.addFlashAttribute("message", "Produto excluído com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Não é possível excluir este produto pois ele possui pedidos vinculados.");
        }
        return "redirect:/produtos/listar";
    }
}