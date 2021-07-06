package br.com.cardapio.controller;

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

import br.com.cardapio.entity.Pedido;
import br.com.cardapio.repository.PedidoRepository;
import br.com.cardapio.service.PedidoService;

@RestController
@RequestMapping("/Pedido")
public class PedidoController {

	@Autowired
	private PedidoService servico;
	@Autowired
	private PedidoRepository repositorio;

	@GetMapping
	public Iterable<Pedido> todosPedidos() {
		return repositorio.findAll();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long codigo) {
		Optional<Pedido> novoPedido = repositorio.findById(codigo);
		if (novoPedido.isEmpty() || novoPedido == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(novoPedido.get());
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Pedido cadastrarPedido(@Valid @RequestBody Pedido pedido) {

		return servico.salvarLanches(pedido);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Pedido> atualizarPedido(@Valid @PathVariable Long codigo, @RequestBody Pedido pedido) {
		Optional<Pedido> ped = repositorio.findById(codigo);
		if (!ped.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		pedido = servico.atualizarLanches(codigo, pedido);
		return ResponseEntity.ok(pedido);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirPedido(@PathVariable Long codigo) {
		if (!repositorio.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		servico.excluirLanches(codigo);
		return ResponseEntity.noContent().build();
	}
}
