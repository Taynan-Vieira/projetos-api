package com.scp.projetos.api.exceptionHandler;

import edu.emory.mathcs.backport.java.util.Arrays;
import lombok.Getter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ProjetosApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
	                                                              HttpHeaders headers, HttpStatus status,
	                                                              WebRequest request) {
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List erros = Arrays.asList(new Erro[]{new Erro(mensagemUsuario, mensagemDesenvolvedor)});

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	                                                              HttpHeaders headers, HttpStatus status,
	                                                              WebRequest request) {
		List<Erro> erros = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
	                                                                   WebRequest request){
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null,
		                                                  LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List erros = Arrays.asList(new Erro[]{new Erro(mensagemUsuario, mensagemDesenvolvedor)});

		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({NoSuchElementException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(NoSuchElementException ex,
	                                                                   WebRequest request){
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null,
		                                                  LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List erros = Arrays.asList(new Erro[]{new Erro(mensagemUsuario, mensagemDesenvolvedor)});

		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
	                                                                    WebRequest request){
		String mensagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null,
		                                                  LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor =  ExceptionUtils.getRootCauseMessage(ex);
		List erros = Arrays.asList(new Erro[]{new Erro(mensagemUsuario, mensagemDesenvolvedor)});

		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
	                                                                    WebRequest request){
		String mensagemUsuario = messageSource.getMessage("mensagem.projeto-existente", null,
		                                                  LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor =  ExceptionUtils.getRootCauseMessage(ex);
		List erros = Arrays.asList(new Erro[]{new Erro(mensagemUsuario, mensagemDesenvolvedor)});

		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private List<Erro> criarListaDeErros(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();

		for(FieldError fieldError : bindingResult.getFieldErrors()){
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String menssagemDesenvolvedor = fieldError.toString();
			erros.add(new Erro(mensagemUsuario, menssagemDesenvolvedor));
		}

		return erros;
	}

	public static class Erro{
		@Getter
		private String mensagemUsuario;
		@Getter
		private String mensagemDesenvolvedor;

		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

	}
}
