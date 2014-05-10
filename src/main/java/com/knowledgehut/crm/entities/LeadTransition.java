package com.knowledgehut.crm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "LEAD_TRANSITION")
public class LeadTransition extends AuditableIdEntity {

    @Column(name = "LEAD_ID")
    private Long caseId;

    @Column(name = "FSTATE")
    private Integer fromState;

    @Column(name = "TSTATE")
    private Integer toState;

    @Column(name = "REMARKS")
    private String remarks;
    
    @Column(name = "EXTERNAL_COMMUNICATION")
    private String externalCommunication;
    
    @Column(name = "INTERNAL_COMMUNICATION")
    private String internalCommunication;
    

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public Integer getFromState() {
		return fromState;
	}

	public void setFromState(Integer fromState) {
		this.fromState = fromState;
	}

	public Integer getToState() {
		return toState;
	}

	public void setToState(Integer toState) {
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
