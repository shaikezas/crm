package com.knowledgehut.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

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
	
	private LeadBean leadBean = new LeadBean();


	
	public LeadBean getLeadBean() {
    return leadBean;
  }

	public List<LeadType> getLeadGroupList() {
	  leadGroupList = leadService.getLeadGroupList();
    return leadGroupList;
  }
	
	public List<LeadType> getLeadTypeList() {
	  logger.info("Getting leadtype list for group id :"+leadBean.getLeadGroupId());
    leadGroupList = leadService.getLeadTypeList(leadBean.getLeadGroupId());
    return leadGroupList;
  }


  public void setLeadBean(LeadBean leadBean) {
    this.leadBean = leadBean;
  }



  public void addLead(){
		try {
			Long leadId = leadService.addLead(leadBean);			
			 showMessage("Lead "+"("+leadId+") created successfully.");
			leadBean.resetValues();
		} catch (Exception e) {
			logger.error("Exception occured when adding new user"+e.getMessage());
			showError(e.getMessage());
		}		
	}
}
	
