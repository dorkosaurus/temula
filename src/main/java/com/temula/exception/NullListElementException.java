package com.temula.exception;

@SuppressWarnings("serial")
public class NullListElementException extends Exception {
	public NullListElementException(String msg){
		super(msg);
	}
	public NullListElementException(){
		super();
	}
}
