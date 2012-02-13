package com.temula.exception;

@SuppressWarnings("serial")
public class NullInputException extends Exception {
	public NullInputException(){
		super();
	}
	public NullInputException(String msg){
		super(msg);
	}
}
