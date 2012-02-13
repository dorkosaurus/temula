package com.temula.exception;

@SuppressWarnings("serial")
public class InvalidInputException extends Exception {
	public InvalidInputException(String msg){
		super(msg);
	}
	public InvalidInputException(){
		super();
	}

}
