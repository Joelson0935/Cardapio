package br.com.cardapio.exceptionhandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cardapio.service.exception.MensagemErro;

@ControllerAdvice
public class ExceptionHandlerMethodTest extends ResponseEntityExceptionHandler {

	@Autowired
	public MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		MensagemDeErroTeste mensagemTeste = new MensagemDeErroTeste();
		mensagemTeste.setMomento(Instant.now());

		List<MensagemDeErroTeste> mensagens = new ArrayList<>();
		for (ObjectError msgError : ex.getBindingResult().getAllErrors()) {
			String campo = ((FieldError) msgError).getField();
			String mensagem = messageSource.getMessage(msgError, LocaleContextHolder.getLocale());
			mensagens.add(new MensagemDeErroTeste(campo, mensagem));
		}

		return super.handleExceptionInternal(ex, mensagemTeste, headers, status, request);
	}
	
	@ExceptionHandler(MensagemErro.class)
	public ResponseEntity<MensagemDeErroTeste> ObjetoNaoEncontrado(MensagemErro m, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		MensagemDeErroTeste erro = new MensagemDeErroTeste();
		erro.setStatus(status.value());
		erro.setMomento(Instant.now());
		erro.setMensagem(m.getMessage());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
}
