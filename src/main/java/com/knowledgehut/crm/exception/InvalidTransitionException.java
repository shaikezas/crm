package com.knowledgehut.crm.exception;



public class InvalidTransitionException extends StateMachineException {

	private static final long serialVersionUID = 1L;
	
	public InvalidTransitionException(String message){
		super(message);
	}
}
