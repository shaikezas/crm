package com.knowledgehut.crm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "LEAD")
public class Lead extends AuditableIdEntity {

    @Column(name = "LEAD_TYPE")
    private Long leadType;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "ACTIVE")
    @Type(type = "yes_no")
    private boolean active;
    
    @Column(name = "FNAME")
    private String firstName;

    @Column(name = "LNAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "COURSE_ID")
    private Long courseId;

    @Column(name = "COUNTRY")
    private Integer countryId;

    @Column(name = "CONTACT")
    private String contact;


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

  public Long getLeadType() {
    return leadType;
  }

  public void setLeadType(Long leadType) {
    this.leadType = leadType;
  }


@Override
public String toString() {
return ReflectionToStringBuilder.toString(this);
}
  
}
