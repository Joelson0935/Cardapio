package br.com.cardapio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cardapio.entity.ItemPedido;

@Repository
public interface ItemPedidoRepository extends CrudRepository<ItemPedido, Long> {

}
