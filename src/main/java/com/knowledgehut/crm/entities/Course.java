package com.knowledgehut.crm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "COURSE")
public class Course  extends AuditableIdEntity{
  
  
  @Column(name = "NAME")
  private String name;
  
  @Column(name = "DESCRIPTION")
  private String description;
  
  @Column(name = "CATEGORY_ID")
  private Long categoryId;
  
  @Column(name = "ACTIVE")
  @Type(type = "yes_no")
  private boolean active;
  
  @Column(name = "KEY_WORDS")
  private String keyWords;

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

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getKeyWords() {
    return keyWords;
  }

  public void setKeyWords(String keyWords) {
    this.keyWords = keyWords;
  }
  
  
  
}
