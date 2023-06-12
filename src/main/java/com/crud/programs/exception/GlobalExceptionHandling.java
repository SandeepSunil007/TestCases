package com.crud.programs.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.crud.programs.response.StudentLoginBodyResponse;

@RestControllerAdvice
public class GlobalExceptionHandling {

	// Handle Specific Exception

	@ExceptionHandler(StudentNotFountException.class)
	public ResponseEntity<StudentLoginBodyResponse> handleStudentNotFoundException(StudentNotFountException exception,
			WebRequest request) {
		return ResponseEntity.ok(StudentLoginBodyResponse.builder().isError(Boolean.TRUE)
				.message(exception.getMessage()).build());
	}

	
	@ExceptionHandler(StudentUpdateNotFoundException.class)
	public ResponseEntity<StudentLoginBodyResponse> handleStudentUpdateNotFoundException(StudentUpdateNotFoundException exception,
			WebRequest request) {
		return ResponseEntity.ok(StudentLoginBodyResponse.builder().isError(Boolean.TRUE)
				.message(exception.getMessage()).build());
	}

	@ExceptionHandler(StudentGetNotFountException.class)
	public ResponseEntity<StudentLoginBodyResponse> handleStudentGetNotFountException(StudentGetNotFountException exception,
			WebRequest request) {
		return ResponseEntity.ok(StudentLoginBodyResponse.builder().isError(Boolean.TRUE)
				.message(exception.getMessage()).build());
	}
	

}
