package com.knowledgehut.crm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "WORK_SHOP_TRANSITION")
public class WorkShopTransition extends AuditableIdEntity {

    @Column(name = "WORK_SHOP_ID")
    private Long courseId;

    @Column(name = "FSTATE")
    private Integer fromState;

    @Column(name = "TSTATE")
    private Integer toState;

    @Column(name = "REMARKS")
    private String remarks;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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
