package com.knowledgehut.crm.common;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component(value = "currentUser")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3801247956898203613L;
	private String userName;
	private Long userId;	
	private String email;
	private Date loginTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

  public Date getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Date loginTime) {
    this.loginTime = loginTime;
  }


	
}