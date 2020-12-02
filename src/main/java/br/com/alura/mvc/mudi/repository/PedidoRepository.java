package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	
//	List<Pedido> findByStatus(StatusPedido aguardando);
	@Cacheable("ultimosPedidosEntregues")
	List<Pedido> findByStatus(StatusPedido aguardando, Pageable paginacao);

	@Query("select p from Pedido p join p.usuario u where u.username = :username")
	List<Pedido> findAllByUsuario(@Param("username")String username);

	@Query("select p from Pedido p join p.usuario u where u.username = :username and p.status = :status")
	List<Pedido> findByStatusEUsuario(@Param("status")StatusPedido valueOf, @Param("username")String username);
}
