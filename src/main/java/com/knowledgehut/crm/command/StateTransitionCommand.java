package com.knowledgehut.crm.command;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({CreateLeadCommand.class})
public abstract class StateTransitionCommand extends Command<CommandResponse> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String remarks;
	
	private Long modifiedBy;
	
	private Long leadId;
	
private String externalCommunication;
  
  private String internalCommunication;
	
	
    //Needed for jaxb marshalling/unmarshalling
    protected StateTransitionCommand() {
    }

    public StateTransitionCommand(Long leadId,Long user,String internalCommunication,String externalCommunication) {
      this.leadId  = leadId;
        this.modifiedBy = user;
        this.externalCommunication = externalCommunication;
        this.internalCommunication = internalCommunication;
    }

    @XmlElement
    public Long getLeadId() {
      return leadId;
    }

    public void setLeadId(Long leadId) {
      this.leadId = leadId;
    }

    @XmlElement
    public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@XmlElement
    public String getRemarks() {
      return remarks;
    }

    public void setRemarks(String remarks) {
      this.remarks = remarks;
    }
    
    @XmlElement
    public String getExternalCommunication() {
      return externalCommunication;
    }

    public void setExternalCommunication(String externalCommunication) {
      this.externalCommunication = externalCommunication;
    }

    @XmlElement
    public String getInternalCommunication() {
      return internalCommunication;
    }

    public void setInternalCommunication(String internalCommunication) {
      this.internalCommunication = internalCommunication;
    }

    
}
