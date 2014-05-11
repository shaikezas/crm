package com.knowledgehut.crm.command;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommandResponse implements Serializable {


	private String remarks;
	

	private Long returnIdentifier;
	

	public Long getReturnIdentifier() {
		return returnIdentifier;
	}

	public void setReturnIdentifier(Long returnIdentifier) {
		this.returnIdentifier = returnIdentifier;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
