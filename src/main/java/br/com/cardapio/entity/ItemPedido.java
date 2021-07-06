package br.com.cardapio.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", timezone = "GMT-3")
	private LocalDateTime dataHora;

	@OneToMany(mappedBy = "itens")
	private List<Lanche> lanches = new ArrayList<>();

	@OneToMany(mappedBy = "itens")
	private List<Bebidas> bebidas = new ArrayList<>();

	@OneToMany(mappedBy = "itens")
	private List<Porcoes> porcoes = new ArrayList<>();

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	private Pedido pedido;

	@Column(precision = 5, scale = 2)
	private BigDecimal valorTotal;

	public ItemPedido() {

	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<Lanche> getLanches() {
		return lanches;
	}

	public List<Bebidas> getBebidas() {
		return bebidas;
	}

	public List<Porcoes> getPorcoes() {
		return porcoes;
	}

	public LocalDateTime getDataHora() {
		dataHora = LocalDateTime.now();
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public BigDecimal getValorTotal() {

		BigDecimal totalSoma = BigDecimal.ZERO;
		BigDecimal totalSoma2 = BigDecimal.ZERO;
		BigDecimal totalSoma3 = BigDecimal.ZERO;

		for (Lanche lanche : lanches) {
			totalSoma = totalSoma.add(lanche.valorTotal());
		}
		for (Bebidas bebida : bebidas) {
			totalSoma2 = totalSoma2.add(bebida.valorTotal());
		}
		for (Porcoes porcao : porcoes) {
			totalSoma3 = totalSoma3.add(porcao.valorTotal());
		}

		valorTotal = totalSoma.add(totalSoma2.add(totalSoma3));
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
