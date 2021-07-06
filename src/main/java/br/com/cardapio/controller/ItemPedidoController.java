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

import br.com.cardapio.entity.ItemPedido;
import br.com.cardapio.repository.ItemPedidoRepository;
import br.com.cardapio.service.ItemPedidoService;

@RestController
@RequestMapping("/ItemPedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService servico;
	@Autowired
	private ItemPedidoRepository repositorio;

	@GetMapping
	public Iterable<ItemPedido> todosItensPedidos() {
		return repositorio.findAll();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<ItemPedido> buscarPorId(@PathVariable Long codigo) {
		Optional<ItemPedido> novoItemPedido = repositorio.findById(codigo);
		if (!novoItemPedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		novoItemPedido.get().getValorTotal();
		return ResponseEntity.ok(novoItemPedido.get());
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ItemPedido cadastrarItemPedido(@Valid @RequestBody ItemPedido itens) {
	
		return servico.salvarItemPedido(itens);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<ItemPedido> atualizarItemPedido(@Valid @PathVariable Long codigo, @RequestBody ItemPedido itens) {

		if (!repositorio.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		itens.setCodigo(codigo);
		itens.getDataHora();
		itens.getValorTotal();
		itens = servico.salvarItemPedido(itens);
		return ResponseEntity.ok(itens);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirItemPedido(@PathVariable Long codigo) {
		if (!repositorio.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		servico.excluir(codigo);
		return ResponseEntity.noContent().build();
	}

	/*
	 * Esta é uma forma mais complexa de fazer o post.
	 *
	 * URI uri =ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").
	 * buildAndExpand(novoPedido.getCodigo()).toUri(); return
	 * ResponseEntity.created(uri).body(novoPedido);
	 */
}
