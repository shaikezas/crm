package com.knowledgehut.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledgehut.crm.common.State;
import com.knowledgehut.crm.entities.Country;
import com.knowledgehut.crm.entities.Course;
import com.knowledgehut.crm.entities.Lead;
import com.knowledgehut.crm.entities.LeadType;
import com.knowledgehut.crm.repositories.CountryRepository;
import com.knowledgehut.crm.repositories.CourseRepository;
import com.knowledgehut.crm.repositories.LeadRepository;
import com.knowledgehut.crm.repositories.LeadTypeRepository;
import com.knowledgehut.crm.util.ConvertUtil;
import com.knowledgehut.crm.web.bean.LeadBean;

@Service("leadService")
public class LeadService {

	private static Logger logger = LoggerFactory.getLogger(LeadService.class);

	
	@Autowired
	private LeadTypeRepository leadTypeRepository;
	
	@Autowired
	private LeadRepository leadRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CountryRepository countryRepository;

	public Long addLead(LeadBean leadBean,Long userId){
	  logger.info("Creating a lead ");
	  Lead lead = ConvertUtil.convertToLead(leadBean);
	  lead.setCreatedBy(userId);
	  lead.setModifiedBy(userId);
	  lead.setStatus(State.NEW.value());
	  logger.info(lead.toString());
	  leadRepository.save(lead);
	  logger.info("Lead created successfully with leadId {}",lead.getId());
	  return lead.getId();
	}
	
	public List<LeadType> getLeadGroupList(){
	 List<LeadType> leadGroups = leadTypeRepository.findLeadGroup();
	 return leadGroups;
	}
	
	public List<Course> getCourseList(){
	  List<Course> courseList = courseRepository.findByActive(true);
	  System.out.println(courseList.size());
	  return courseList;
	}
	
	public List<Country> getCountryList(){
    List<Country> countryList = countryRepository.findAll();
    return countryList;
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
