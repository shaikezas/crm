package com.knowledgehut.crm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "WORK_SHOP_REGISTRATION")
public class WorkShopRegistration extends AuditableIdEntity {

    @Column(name = "WORK_SHOP_ID")
    private Long workShopId;

    @Column(name = "FNAME")
    private String firstName;

    @Column(name = "LNAME")
    private String lastName;

    @Column(name = "QTY")
    private Integer quantity;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "CURRENCY")
    private String currecny;
    
    @Column(name = "CITY")
    private Integer cityId;
    
    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DELETED")
    @Type(type = "yes_no")
    private boolean active;
    

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


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public String getCurrecny() {
		return currecny;
	}


	public void setCurrecny(String currecny) {
		this.currecny = currecny;
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


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


}
