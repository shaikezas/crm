package com.knowledgehut.crm.service;

import javax.security.auth.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledgehut.crm.common.CurrentUser;

@Service
public class LoginService {

	
	@Autowired
	private CurrentUser currentUser;
	
	private static Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	public Subject login(String loginName, String password) {
	  return null;
}


}
