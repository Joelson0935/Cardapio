package br.com.cardapio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardapio.entity.Porcoes;
import br.com.cardapio.repository.PorcoesRepository;
import br.com.cardapio.service.PorcoesService;

@RestController
@RequestMapping("/Porcoes")
public class PorcoesController {

	@Autowired
	private PorcoesService servico;
	@Autowired
	private PorcoesRepository repositorio;

	@GetMapping
	public Iterable<Porcoes> todasPorcoes() {
		return repositorio.findAll();
	}

	@GetMapping("/Busca")
	public List<Porcoes> porcoesPorNome(String porcao) {
		return repositorio.findByPorcaoContaining(porcao);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Porcoes> buscarPorId(@PathVariable Long codigo) {
		Optional<Porcoes> novaPorcao = repositorio.findById(codigo);
		if (novaPorcao.isEmpty() || novaPorcao == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(novaPorcao.get());
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Porcoes cadastrarPorcao(@RequestBody Porcoes porcao) {

		return servico.salvarPorcoes(porcao);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Porcoes> atualizarPorcao(@PathVariable Long codigo, @RequestBody Porcoes porcao) {
		Optional<Porcoes> p = repositorio.findById(codigo);
		if (!p.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		porcao = servico.atualizarPorcoes(codigo, porcao);
		return ResponseEntity.ok(porcao);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirPorcao(@PathVariable Long codigo) {
		if (!repositorio.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		servico.excluirPorcoes(codigo);
		return ResponseEntity.noContent().build();
	}
}
