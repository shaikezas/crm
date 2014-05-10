package com.knowledgehut.crm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "USER_COURSE")
public class UserCourse extends AuditableIdEntity {

    @Column(name = "USER_REGION_ID")
    private Integer userId;

    @Column(name = "COURSE_ID")
    private Integer courseId;

    @Column(name = "DELETED")
    @Type(type = "yes_no")
    private Boolean deleted;


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getCourseId() {
		return courseId;
	}


	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}


	public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


}
