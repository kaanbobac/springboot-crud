package kaan.demo.spring.crudapp.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import kaan.demo.spring.crudapp.exception.ExceptionResponse;
import kaan.demo.spring.crudapp.exception.UserNotFoundException;

@RestController
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception ex, WebRequest request) {
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)),
				HttpStatus.NOT_FOUND);
	}
}
