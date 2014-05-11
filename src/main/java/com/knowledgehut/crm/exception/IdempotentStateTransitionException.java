package com.knowledgehut.crm.exception;


public class IdempotentStateTransitionException extends StateMachineException {

	public IdempotentStateTransitionException(String message) {
		super(message);
	}
}
