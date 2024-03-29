package br.com.felipeduarte.gerenciadordeestudo.controller.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
 

@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<ErrorModel> internalServerError(ObjectNotFoundException ex, HttpServletRequest request){
		ErrorModel em = new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Internal Serve Erro",ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(em);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErrorModel> notFoundExceptionHandler(ObjectNotFoundException ex,
			HttpServletRequest request){
		
		ErrorModel em = new ErrorModel(HttpStatus.NOT_FOUND.value(),"Not Found",ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(em);
	}
	
	@ExceptionHandler(ObjectBadRequestException.class)
	public ResponseEntity<ErrorModel> badRequestExceptionHandler(ObjectBadRequestException ex,
			HttpServletRequest request){
		
		ErrorModel em  = new ErrorModel(HttpStatus.BAD_REQUEST.value(),"Bad Request",ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(em);
	
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ErrorModel> authorization(AuthorizationException ex,
			HttpServletRequest request){
		
		ErrorModel em = new ErrorModel(HttpStatus.FORBIDDEN.value(),"Acesso negado",ex.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(em);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectErrorArgument> errors = ex.getBindingResult().getFieldErrors().stream()
			.map(error -> new ObjectErrorArgument(error.getDefaultMessage(),error.getField()))
			.collect(Collectors.toList());
			
		ErrorArgument ea = new ErrorArgument(status.value(),status.toString(),"Erro de Validação!");
		ea.setErrors(errors);
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ea);
	}

}
