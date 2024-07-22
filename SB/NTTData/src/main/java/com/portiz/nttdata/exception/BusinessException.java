package com.portiz.nttdata.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -1376601428131908124L;
	private String code;
	private HttpStatus status;

	public BusinessException(String code, HttpStatus status, String message) {
		super(message);
		this.code = code;
		this.status = status;
	}

}
