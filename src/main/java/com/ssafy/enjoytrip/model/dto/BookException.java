package com.ssafy.enjoytrip.model.dto;

public class BookException extends RuntimeException{
	public BookException() {}
	public BookException(String msg) {
		super(msg);
	}
}
