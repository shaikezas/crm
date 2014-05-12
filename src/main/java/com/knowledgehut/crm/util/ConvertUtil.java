package com.knowledgehut.crm.util;

import java.util.Date;

import com.knowledgehut.crm.entities.Lead;
import com.knowledgehut.crm.web.bean.LeadBean;

public class ConvertUtil {

  
  public static Lead convertToLead(LeadBean leadBean){
    Lead lead  = new Lead();
    lead.setActive(true);
    lead.setContact(leadBean.getContact());
    lead.setCountryId(leadBean.getCountryId());
    lead.setCourseId(leadBean.getCourseId());
    lead.setCreatedDate(new Date());
    lead.setDescription(leadBean.getDescription());
    lead.setEmail(leadBean.getEmail());
    lead.setFirstName(leadBean.getFirstName());
    lead.setLastName(leadBean.getLastName());
    lead.setLeadType(leadBean.getLeadTypeId());
    return lead;
        
  }
}
