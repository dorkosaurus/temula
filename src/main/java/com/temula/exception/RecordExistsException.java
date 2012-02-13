package com.temula.exception;

@SuppressWarnings("serial")
public class RecordExistsException extends Exception {
	public RecordExistsException(String msg){
		super(msg);
	}
	public RecordExistsException(){
		super();
	}

}
