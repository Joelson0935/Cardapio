package br.com.cardapio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cardapio.entity.Pedido;


@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

	
}
