package com.knowledgehut.crm.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowledgehut.crm.common.CurrentUser;
import com.knowledgehut.crm.util.ApplicationConstants;

public abstract class BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2909476075388786249L;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected CurrentUser currentUser;
	
    public String getUserRole() {
        return "To be added!";
    }

    /**
     * Util method to suffix '?faces-redirect=true' to the page
     * 
     * @param page
     * @return
     */
    protected String redirect(String page) {
        if (page != null && page.contains("?")) {
            return page + "&faces-redirect=true";
        } else {
            return page + "?faces-redirect=true";
        }
    }

    protected String homePage() {
        return "/jsp/dashboard.xhtml?faces-redirect=true";
    }

    protected String loginPage() {
        return ApplicationConstants.USER_LOGIN_PAGE_URI;
    }
    
    protected String logoutPage() {
        return ApplicationConstants.USER_LOGOUT_PAGE;
    }

    protected Log getLogger() {
        return LogFactory.getLog(this.getClass());
    }

    protected void showPopUpError(String message) {
		RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message)); 
	}
    
    protected void showError(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", message));
	}
	
    protected void showMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", message));
	}
	
	protected void showMessage(String clientId,String message) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, "", message));
	}
	
	protected void showWarning(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", message));
	}
	public CurrentUser getCurrentUser() {
		return currentUser;
	}
}