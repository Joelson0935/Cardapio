package br.com.cardapio.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import br.com.cardapio.entity.Lanche;
import br.com.cardapio.repository.LancheRepository;
import br.com.cardapio.service.LancheService;

@RestController
@RequestMapping("/Lanche")
public class LancheController {

	@Autowired
	private LancheService servico;
	@Autowired
	private LancheRepository repositorio;

	@GetMapping
	public Iterable<Lanche> todosLanches() {
		return repositorio.findAll();
	}

	@GetMapping("/Busca")
	public List<Lanche> lanchesPorNome(String nome) {
		return repositorio.findByLancheContaining(nome);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Lanche> buscarPorId(@PathVariable Long codigo) {
		Optional<Lanche> novoLanche = repositorio.findById(codigo);
		if (novoLanche.isEmpty() || novoLanche == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(novoLanche.get());
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Lanche cadastrarLanche(@Valid @RequestBody Lanche lanche) {

		return servico.salvarLanches(lanche);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Lanche> atualizarLanche(@Valid @PathVariable Long codigo, @RequestBody Lanche lanche) {
		Optional<Lanche> l = repositorio.findById(codigo);
		if (!l.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		lanche = servico.atualizarLanches(codigo, lanche);
		return ResponseEntity.ok(lanche);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirLanche(@PathVariable Long codigo) {
		if (!repositorio.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		servico.excluirLanches(codigo);
		return ResponseEntity.noContent().build();
	}
}
