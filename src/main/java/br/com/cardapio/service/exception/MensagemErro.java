package br.com.cardapio.service.exception;

public class MensagemErro extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MensagemErro(String message) {
		super(message);
	}
	
	

}
