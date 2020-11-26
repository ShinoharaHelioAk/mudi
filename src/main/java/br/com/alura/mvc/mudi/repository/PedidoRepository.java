package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
//	@PersistenceContext
//	private EntityManager entityManager;
	
//	public List<Pedido> recuperaTodosOsPedidos() {
//		TypedQuery<Pedido> query = entityManager.createQuery("select p from Pedido p", Pedido.class);
//		return query.getResultList();
//	}
	
	//Não precisa declarar, pois a interface JpaRepository já possui esse método de forma implícita.
//	public List<Pedido> findAll();
	
	List<Pedido> findByStatus(StatusPedido aguardando);
}
