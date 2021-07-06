package br.com.cardapio.exceptionhandler;

import java.time.Instant;
import java.util.ArrayList;

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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	public MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		var erros = new ArrayList<ErrorMessage.Erro>();

		for (ObjectError errado : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) errado).getField();
			String mensagem = messageSource.getMessage(errado, LocaleContextHolder.getLocale());
			erros.add(new ErrorMessage.Erro(nome, mensagem));
		}

		var mensagemErro = new ErrorMessage();
		mensagemErro.setStatus(status.value());
		mensagemErro.setMomento(Instant.now());
		mensagemErro.setErros(erros);

		return super.handleExceptionInternal(ex, mensagemErro, headers, status, request);
	}

}
