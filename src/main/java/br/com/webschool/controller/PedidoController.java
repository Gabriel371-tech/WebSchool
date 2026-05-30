package br.com.webschool.controller;

import br.com.webschool.entity.Pedido;
import br.com.webschool.service.PedidoService;
import br.com.webschool.service.AlunoService;
import br.com.webschool.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoService.findAll());
        return "pedido/listaPedidos";
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("alunos", alunoService.findAll());
        model.addAttribute("produtos", produtoService.findAll());
        return "pedido/formularioPedido";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Pedido pedido) {
        pedidoService.save(pedido);
        return "redirect:/pedidos/listar";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable Integer id, Model model) {
        Pedido pedido = pedidoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        model.addAttribute("pedido", pedido);
        return "pedido/detalhesPedido";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id, RedirectAttributes attributes) {
        try {
            pedidoService.deleteById(id);
            attributes.addFlashAttribute("message", "Pedido excluído com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Não é possível excluir este pedido pois ele possui itens vinculados.");
        }
        return "redirect:/pedidos/listar";
    }

    @PostMapping("/atualizar-status/{id}")
    public String atualizarStatus(@PathVariable Integer id, @RequestParam String status) {
        pedidoService.updateStatus(id, status);
        return "redirect:/pedidos/listar";
    }
}