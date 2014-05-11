package com.knowledgehut.crm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * This table holds the sub states information in the dropship fc application.
 *
 */
@Entity
@Table(name="LEAD_STATE_MASTER")
public class LeadState extends AuditableIdEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7086221238103722562L;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	 @Column(name = "DELETED")
	  @Type(type = "yes_no")
	  private boolean isDeleted;
	
	@Column(name="DESCRIPTION", nullable=false)
	private String description;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
