package com.knowledgehut.crm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "CASE_TRANSITION")
public class CaseTransition extends AuditableIdEntity {

    @Column(name = "CASE_ID")
    private Long caseId;

    @Column(name = "FSTATE")
    private Integer fromState;

    @Column(name = "TSTATE")
    private Integer toState;

    @Column(name = "REMARKS")
    private String remarks;

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
}
