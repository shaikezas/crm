package com.knowledgehut.crm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class AuditableIdEntity extends IdEntity {

	// CREATEDBYbigint(20) NOT NULL
	@Column(name = "CREATEDBY", length = 20)
	private Long createdBy;
	
	@Column(name = "MODIFIEDBY", length = 20)
  private Long modifiedBy;

	@Column(name = "CREATED_DATE", nullable = false)
	private Date createdDate = new Date();

	@Column(name = "MODIFIED_DATE", nullable = false)
	private Date modifiedDate = new Date();

	@PreUpdate
	public void onUpdate(){
		this.setModifiedDate(new Date());
	}

	@PrePersist
	public void onPersist(){
		setCreatedDate(new Date());
		onUpdate();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	public Long getModifiedBy() {
		return modifiedBy;
	}
	
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
