package com.knowledgehut.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledgehut.crm.entities.LeadType;
import com.knowledgehut.crm.repositories.LeadTypeRepository;
import com.knowledgehut.crm.web.bean.LeadBean;

@Service("leadService")
public class LeadService {

	private static Logger logger = LoggerFactory.getLogger(LeadService.class);

	
	@Autowired
	private LeadTypeRepository leadTypeRepository;
	

	public Long addLead(LeadBean leadBean){
	  logger.info("Lead created successfully");
	  return 1L;
	}
	
	public List<LeadType> getLeadGroupList(){
	 List<LeadType> leadGroups = leadTypeRepository.findLeadGroup();
	 return leadGroups;
	}
	
	public List<LeadType> getLeadTypeList(Long leadGroupId){
	  if(leadGroupId!=null){
	   List<LeadType> leadtypes = leadTypeRepository.findLeadTypeByGroup(leadGroupId);
	   return leadtypes;
	  }else{
	    return new ArrayList<LeadType>();
	  }
	  }
}
