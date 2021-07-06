package br.com.cardapio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardapio.entity.Pedido;
import br.com.cardapio.repository.PedidoRepository;
import br.com.cardapio.service.exception.MensagemErro;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;

	public Pedido salvarLanches(Pedido pedido) {

		return repositorio.save(pedido);
	}

	public Pedido atualizarLanches(Long codigo, Pedido pedido) {
		if (!repositorio.existsById(codigo)) {
			throw new MensagemErro("Código não encontrado, Favor colocar um código válido.");
		}
		pedido.setCodigo(codigo);

		return repositorio.save(pedido);
	}

	public void excluirLanches(Long codigo) {

		repositorio.deleteById(codigo);
	}

}
