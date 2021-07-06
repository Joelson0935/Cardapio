package br.com.cardapio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardapio.entity.Porcoes;
import br.com.cardapio.repository.PorcoesRepository;
import br.com.cardapio.service.exception.MensagemErro;

@Service
public class PorcoesService {

	@Autowired
	private PorcoesRepository repositorio;

	public Porcoes salvarPorcoes(Porcoes porcao) {
		porcao.getItens().getCodigo();
		
		return repositorio.save(porcao);
	}

	public Porcoes atualizarPorcoes(Long codigo, Porcoes porcao) {
		if (!repositorio.existsById(codigo)) {
			throw new MensagemErro("Código não encontrado, Favor colocar um código válido.");
		}
		porcao.setCodigo(codigo);

		return repositorio.save(porcao);
	}

	public void excluirPorcoes(Long codigo) {

		repositorio.deleteById(codigo);
	}

}
