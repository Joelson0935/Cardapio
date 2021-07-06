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

import br.com.cardapio.entity.Bebidas;
import br.com.cardapio.repository.BebidasRepository;
import br.com.cardapio.service.BebidasService;

@RestController
@RequestMapping("/Bebidas")
public class BebidasController {

	@Autowired
	private BebidasService servico;
	@Autowired
	private BebidasRepository repositorio;

	@GetMapping("/Busca")
	public List<Bebidas> bebidasPorNome(String nome) {
		return repositorio.findByBebidaContaining(nome);
	}
	
	@GetMapping
	public Iterable<Bebidas> todasBebidas() {
		return repositorio.findAll();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Bebidas> buscarPorId(@PathVariable Long codigo) {
		Optional<Bebidas> novaBebida = repositorio.findById(codigo);
		if (novaBebida.isEmpty() || novaBebida == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(novaBebida.get());
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Bebidas cadastrarBebida(@Valid @RequestBody Bebidas bebida) {

		return servico.salvarBebidas(bebida);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Bebidas> atualizarBebida(@Valid @PathVariable Long codigo, @RequestBody Bebidas bebida) {
		Optional<Bebidas> b = repositorio.findById(codigo);
		if(!b.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		bebida = servico.atualizarBebidas(codigo, bebida);
		return ResponseEntity.ok(bebida);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirBebida(@PathVariable Long codigo) {
		if (!repositorio.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		servico.excluirBebidas(codigo);
		return ResponseEntity.noContent().build();
	}
}
