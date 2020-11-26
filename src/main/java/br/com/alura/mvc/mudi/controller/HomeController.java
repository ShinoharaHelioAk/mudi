package br.com.alura.mvc.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
//	@PersistenceContext
//	private EntityManager entityManager;
	
	//Recupera uma instância do objeto abaixo. É uma Injeção de Dependência.
	@Autowired
	private PedidoRepository pedidoRepository;
	
//	@GetMapping("/home")
	@GetMapping
	public String home(Model model) {
//		Pedido pedido = new Pedido();
//		pedido.setNomeProduto("Celular Xiaomi Redmi Note 8 Pro 128 GB 6 GB Global Mineral Grey");
//		pedido.setUrlImagem("https://images-na.ssl-images-amazon.com/images/I/61CSVqU11NL._AC_SL1000_.jpg");
//		pedido.setUrlProduto("https://www.amazon.com.br/Celular-Xiaomi-Redmi-Global-Mineral/dp/B07YB7NLY7/ref=sr_1_1?__mk_pt_BR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=27I94KR5WW80Z&dchild=1&keywords=xiaomi+redmi+note+8+pro+6+gb+128gb+celular+64mp+vers%C3%A3o+global&qid=1606159087&sprefix=xiaomi+redmi+note+8+pro+6+gb%2Caps%2C336&sr=8-1");
//		pedido.setDescricao("descricao do pedido");
//		
//		List<Pedido> pedidos = Arrays.asList(pedido);
		
//		TypedQuery<Pedido> query = entityManager.createQuery("select p from Pedido p", Pedido.class);
//		List<Pedido> pedidos = query.getResultList();
		
		//List<Pedido> pedidos = pedidoRepository.recuperaTodosOsPedidos();
		List<Pedido> pedidos = pedidoRepository.findAll();
		model.addAttribute("pedidos", pedidos);
		
		return "home";
	}
	
	@GetMapping("/{status}")
	public String porStatus(@PathVariable("status") String status, Model model) {
		//Lembrando que esse é o conceito de Derived Queries.
		//pedidoRepository.findByStatus(StatusPedido.AGUARDANDO)
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		
		return "home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}

}
