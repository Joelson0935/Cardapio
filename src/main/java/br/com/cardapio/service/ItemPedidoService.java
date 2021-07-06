package br.com.cardapio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardapio.entity.ItemPedido;
import br.com.cardapio.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repositorio;

	public ItemPedido salvarItemPedido(ItemPedido itens) {
		
		itens.getPedido().getCodigo();
		itens.getDataHora();
		itens.getValorTotal();
	
		return repositorio.save(itens);
	}

	public void excluir(Long codigo) {

		repositorio.deleteById(codigo);
	}
}
