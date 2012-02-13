package com.temula.exception;

@SuppressWarnings("serial")
public class RecordDoesNotExistException extends Exception {
	public RecordDoesNotExistException(String msg){
		super(msg);
	}
	public RecordDoesNotExistException(){
		super();
	}

}
