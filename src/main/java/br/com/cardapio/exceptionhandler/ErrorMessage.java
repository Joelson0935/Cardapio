package br.com.cardapio.exceptionhandler;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class ErrorMessage {

	private Integer status;
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy 'T' HH:mm:ss 'Z'", timezone = "GMT")
	private Instant momento;
	private List<Erro> erros;

	public static class Erro {

		private String campo;
		private String mensagem;

		public Erro(String campo, String mensagem) {
			super();
			this.campo = campo;
			this.mensagem = mensagem;
		}

		public String getCampo() {
			return campo;
		}

		public void setCampo(String campo) {
			this.campo = campo;
		}

		public String getMensagem() {
			return mensagem;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}

	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Instant getMomento() {
		return momento;
	}

	public void setMomento(Instant momento) {
		this.momento = momento;
	}

	public List<Erro> getErros() {
		return erros;
	}

	public void setErros(List<Erro> erros) {
		this.erros = erros;
	}

}
