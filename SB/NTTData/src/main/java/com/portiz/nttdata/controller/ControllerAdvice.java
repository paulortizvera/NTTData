package com.portiz.nttdata.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.portiz.nttdata.dto.ErrorDTO;
import com.portiz.nttdata.exception.BusinessException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity<ErrorDTO> requestExceptionHandler(BusinessException ex) {
		ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
