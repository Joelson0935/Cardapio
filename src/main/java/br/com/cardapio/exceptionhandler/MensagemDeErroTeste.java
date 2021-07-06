package br.com.cardapio.exceptionhandler;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class MensagemDeErroTeste {

	
	private Integer status;
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy 'T' HH:mm:ss 'Z'", timezone = "GMT")
	private Instant momento;
	private String campo;
	private String mensagem;
	private String path;

	public MensagemDeErroTeste() {
		super();
	}

	public MensagemDeErroTeste(Integer status, Instant momento, String campo, String mensagem, String path) {
		super();
		this.status = status;
		this.momento = momento;
		this.campo = campo;
		this.mensagem = mensagem;
		this.path = path;
	}

	public MensagemDeErroTeste(Integer status, Instant momento, String mensagem) {
		super();
		this.status = status;
		this.momento = momento;
		this.mensagem = mensagem;
	}

	public MensagemDeErroTeste(String campo, String mensagem) {
		super();
		this.campo = campo;
		this.mensagem = mensagem;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
