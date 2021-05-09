package kaan.demo.spring.crudapp.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExceptionResponse {
	Date time;
	String message;
	String details;
}
