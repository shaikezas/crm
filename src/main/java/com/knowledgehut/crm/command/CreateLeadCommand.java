package com.knowledgehut.crm.command;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CreateLeadCommand extends StateTransitionCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected CreateLeadCommand() {}

	public CreateLeadCommand(Long leadId,Long user,String internalCommunication ,String externalCommunication) {
		super(leadId,user,internalCommunication,externalCommunication);
	}

	
	
	
}
