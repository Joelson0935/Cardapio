package br.com.cardapio.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Bebidas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	@Size(max = 15)
	private String bebida;

	@Min(value = 0)
	private Integer quantidade;

	@Column(precision = 5, scale = 2)
	private BigDecimal preco;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	private ItemPedido itens;

	public Bebidas() {
		super();
	}

	public Bebidas(Long codigo, @Size(max = 15) String bebida, @Min(0) @NotNull Integer quantidade, BigDecimal preco) {
		super();
		this.codigo = codigo;
		this.bebida = bebida;
		this.quantidade = quantidade;
		this.preco = preco;

	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getBebida() {
		return bebida;
	}

	public void setBebida(String bebida) {
		this.bebida = bebida;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public ItemPedido getItens() {
		return itens;
	}

	public void setItens(ItemPedido itens) {
		this.itens = itens;
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
		Bebidas other = (Bebidas) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public BigDecimal valorTotal() {

		BigDecimal valor = this.preco.multiply(new BigDecimal(this.quantidade));

		return valor;
	}
}
