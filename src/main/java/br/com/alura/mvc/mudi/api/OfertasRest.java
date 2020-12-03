package br.com.alura.mvc.mudi.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	public Oferta criaOferta(RequisicaoNovaOferta requisicao) {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(requisicao.getPedidoId());
		
		if (!pedidoBuscado.isPresent()) {
			return null;
		}
		
		Pedido pedido = pedidoBuscado.get();
		Oferta novaOferta = requisicao.toOferta();
		novaOferta.setPedido(pedido);
		pedido.getOfertas().add(novaOferta);
		
		//Ao salvar o pedido, vai salvar a oferta também por causa do Cascade ALL
		//que está na declaração private List<Oferta> ofertas; da classe Pedido.
		pedidoRepository.save(pedido);
		
		return novaOferta;
	}
}
