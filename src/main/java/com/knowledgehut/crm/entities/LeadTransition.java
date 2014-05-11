package com.knowledgehut.crm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "LEAD_TRANSITION")
public class LeadTransition extends AuditableIdEntity {

    @Column(name = "LEAD_ID")
    private Long leadId;

    @Column(name = "FSTATE")
    private Long fromState;

    @Column(name = "TSTATE")
    private Long toState;

    @Column(name = "REMARKS")
    private String remarks;
    
    @Column(name = "EXTERNAL_COMMUNICATION")
    private String externalCommunication;
    
    @Column(name = "INTERNAL_COMMUNICATION")
    private String internalCommunication;
    


	public Long getLeadId() {
      return leadId;
    }

    public void setLeadId(Long leadId) {
      this.leadId = leadId;
    }

  public Long getFromState() {
		return fromState;
	}

	public void setFromState(Long fromState) {
		this.fromState = fromState;
	}

	public Long getToState() {
		return toState;
	}

	public void setToState(Long toState) {
		this.toState = toState;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

  public String getExternalCommunication() {
    return externalCommunication;
  }

  public void setExternalCommunication(String externalCommunication) {
    this.externalCommunication = externalCommunication;
  }

  public String getInternalCommunication() {
    return internalCommunication;
  }

  public void setInternalCommunication(String internalCommunication) {
    this.internalCommunication = internalCommunication;
  }
	
	
}
