package com.douglas.vendas.online.exceptions;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 4536714062807686835L;

	public BadRequestException(String message) {
        super(message);
	}
}
