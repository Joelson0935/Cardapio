package br.com.cardapio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardapio.entity.Lanche;
import br.com.cardapio.repository.LancheRepository;
import br.com.cardapio.service.exception.MensagemErro;

@Service
public class LancheService {

	@Autowired
	private LancheRepository repositorio;

	public Lanche salvarLanches(Lanche lanche) {
		lanche.getItens().getCodigo();
		
		return repositorio.save(lanche);
	}

	public Lanche atualizarLanches(Long codigo, Lanche lanche) {
		if (!repositorio.existsById(codigo)) {
			throw new MensagemErro("Código não encontrado, Favor colocar um código válido.");
		}
		lanche.setCodigo(codigo);

		return repositorio.save(lanche);
	}

	public void excluirLanches(Long codigo) {

		repositorio.deleteById(codigo);
	}

}
