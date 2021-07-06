package br.com.cardapio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardapio.entity.Bebidas;
import br.com.cardapio.repository.BebidasRepository;
import br.com.cardapio.service.exception.MensagemErro;

@Service
public class BebidasService {

	@Autowired
	private BebidasRepository repositorio;

	public Bebidas salvarBebidas(Bebidas bebida) {
		bebida.getItens().getCodigo();
		
		return repositorio.save(bebida);
	}

	public Bebidas atualizarBebidas(Long codigo, Bebidas bebida) {
		if (!repositorio.existsById(codigo)) {
			throw new MensagemErro("Código não encontrado, Favor colocar um código válido.");
		}
		bebida.setCodigo(codigo);

		return repositorio.save(bebida);
	}

	public void excluirBebidas(Long codigo) {

		repositorio.deleteById(codigo);
	}
}
