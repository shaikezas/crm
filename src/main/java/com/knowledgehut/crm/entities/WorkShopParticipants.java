package com.knowledgehut.crm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "WORK_SHOP_PARTICIPANTS")
public class WorkShopParticipants extends AuditableIdEntity {

    @Column(name = "WORK_SHOP_ID")
    private Long workShopId;

    @Column(name = "FNAME")
    private String firstName;

    @Column(name = "LNAME")
    private String lastName;

    @Column(name = "CITY")
    private Integer cityId;
    
    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DELETED")
    @Type(type = "yes_no")
    private boolean active;
    
    @Column(name = "INVOICE_ID")
    private Long invoiceId;

    @Column(name = "REMARKS")
    private String remarks;

	public Long getWorkShopId() {
		return workShopId;
	}

	public void setWorkShopId(Long workShopId) {
		this.workShopId = workShopId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



}
