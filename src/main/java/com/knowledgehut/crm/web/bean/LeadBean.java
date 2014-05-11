package com.knowledgehut.crm.web.bean;

import java.io.Serializable;

/**
 * 
 * @author Swarnalatha.K
 * 
 */
public class LeadBean implements FormBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email;
	private String firstName;
	private String lastName;
  private Long leadTypeId;
  private Long leadGroupId;
   private String description;

   private Integer status;

   private boolean active;

   private Long courseId;

   private Integer countryId;

   private String contact;


	public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
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


  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public Integer getStatus() {
    return status;
  }


  public void setStatus(Integer status) {
    this.status = status;
  }


  public boolean isActive() {
    return active;
  }


  public void setActive(boolean active) {
    this.active = active;
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





  public Long getLeadGroupId() {
    return leadGroupId;
  }


  public void setLeadGroupId(Long leadGroupId) {
    this.leadGroupId = leadGroupId;
  }


  public Long getLeadTypeId() {
    return leadTypeId;
  }


  public void setLeadTypeId(Long leadTypeId) {
    this.leadTypeId = leadTypeId;
  }


  public void resetValues() {
    email = null;
    firstName = null;
    lastName = null;
    leadTypeId = null;
    leadGroupId = null;
    description = null;
    status = null;
    active = false;
    courseId = null;
    countryId = null;
    contact = null;

	}
}
