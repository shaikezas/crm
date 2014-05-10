package com.knowledgehut.crm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "USER_REGION")
public class UserRegion extends AuditableIdEntity {

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "CITY")
    private Integer cityId;

    @Column(name = "COUNTRY")
    private Integer country;

    @Column(name = "REGION")
    private Integer region;

   
    @Column(name = "DELETED")
    @Type(type = "yes_no")
    private Boolean deleted;


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getCityId() {
		return cityId;
	}


	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}


	public Integer getCountry() {
		return country;
	}


	public void setCountry(Integer country) {
		this.country = country;
	}


	public Integer getRegion() {
		return region;
	}


	public void setRegion(Integer region) {
		this.region = region;
	}


	public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}



}
