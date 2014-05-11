package com.knowledgehut.crm.exception;

public class StateMachineException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String errcode;
	
	public StateMachineException(String errcode, String message){
		super(message);
		this.errcode=errcode;
	}

	public StateMachineException(String message){
		super(message);
	}
	
	public StateMachineException(String message,Throwable cause){
		super(message, cause);
	}
	
	public String getErrCode(){
		return errcode;
	}
	
}
