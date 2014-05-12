package com.knowledgehut.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.knowledgehut.crm.common.CurrentUser;
import com.knowledgehut.crm.entities.Country;
import com.knowledgehut.crm.entities.Course;
import com.knowledgehut.crm.entities.LeadType;
import com.knowledgehut.crm.service.LeadService;
import com.knowledgehut.crm.web.bean.LeadBean;

@Component("leadController")
@Scope(value = "view", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LeadController extends BaseController {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private LeadService leadService;

	private List<LeadType> leadGroupList;

  private List<LeadType> leadList;
  
  private List<Course> courseList;
  
  private List<Country> countryList;
  
  @Autowired
  private CurrentUser currentUser;
	
	private LeadBean leadBean = new LeadBean();


	
	public LeadBean getLeadBean() {
    return leadBean;
  }

	public List<LeadType> getLeadGroupList() {
	  leadGroupList = leadService.getLeadGroupList();
    return leadGroupList;
  }
	
	
	public List<Course> getCourseList(){
	  courseList = leadService.getCourseList();
	  return courseList;
	}
	
	public List<Country> getCountryList(){
    countryList = leadService.getCountryList();
    return countryList;
  }
	
	
	public List<LeadType> getLeadTypeList() {
	  logger.info("Getting leadtype list for group id :"+leadBean.getLeadGroupId());
	  if(leadBean.getLeadGroupId()==null){
	    leadBean.setLeadGroupId(leadGroupList.get(0).getId());
	  }
    leadGroupList = leadService.getLeadTypeList(leadBean.getLeadGroupId());
    return leadGroupList;
  }


  public void setLeadBean(LeadBean leadBean) {
    this.leadBean = leadBean;
  }



  public void addLead(){
		try {
		  String userName = currentUser.getUserName();
		  logger.info("User Name ...{} and UserId..{}",userName,currentUser.getUserId());
			Long leadId = leadService.addLead(leadBean,currentUser.getUserId());			
			 showMessage("Lead "+"("+leadId+") created successfully.");
			leadBean.resetValues();
		} catch (Exception e) {
			logger.error("Exception occured when adding new user"+e.getMessage());
			showError(e.getMessage());
		}		
	}
}
	
