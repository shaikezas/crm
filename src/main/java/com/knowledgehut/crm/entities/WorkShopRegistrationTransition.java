package com.knowledgehut.crm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "WORK_SHOP_REG_TRANSITION")
public class WorkShopRegistrationTransition extends AuditableIdEntity {

    @Column(name = "WORK_SHOP_REG_ID")
    private Long workShopRegId;

    @Column(name = "FSTATE")
    private Integer fromState;

    @Column(name = "TSTATE")
    private Integer toState;

    @Column(name = "REMARKS")
    private String remarks;

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

	public Long getWorkShopRegId() {
		return workShopRegId;
	}

	public void setWorkShopRegId(Long workShopRegId) {
		this.workShopRegId = workShopRegId;
	}

}
