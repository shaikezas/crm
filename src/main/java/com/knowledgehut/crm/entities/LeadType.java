package com.knowledgehut.crm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "LEAD_TYPE")
public class LeadType extends AuditableIdEntity {

    @Column(name = "LEAD_TYPE")
    private String leadType;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "GROUP_ID")
    private Long groupId;

    @Column(name = "ACTIVE")
    @Type(type = "yes_no")
    private boolean active;

    public String getLeadType() {
      return leadType;
    }

    public void setLeadType(String leadType) {
      this.leadType = leadType;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public Long getGroupId() {
      return groupId;
    }

    public void setGroupId(Long groupId) {
      this.groupId = groupId;
    }

    public boolean isActive() {
      return active;
    }

    public void setActive(boolean active) {
      this.active = active;
    }
    



}
