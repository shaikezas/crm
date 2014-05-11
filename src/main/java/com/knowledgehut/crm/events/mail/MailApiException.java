package com.knowledgehut.crm.events.mail;

public class MailApiException extends RuntimeException {
	
	public MailApiException(){
		super();
	}
	
	public MailApiException(String message){
		super(message);
	}
	
	public MailApiException(String message, Throwable cause){
		super(message, cause);
	}
	
	public MailApiException(Throwable cause){
		super(cause);
	}

}
