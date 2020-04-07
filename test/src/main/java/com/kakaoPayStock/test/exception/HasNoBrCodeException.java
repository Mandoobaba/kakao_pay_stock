package com.kakaoPayStock.test.exception;

public class HasNoBrCodeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public HasNoBrCodeException() {
		super("br Code Not found");
	}
	
	public HasNoBrCodeException(String msg) {
		super(msg);
	}
}
