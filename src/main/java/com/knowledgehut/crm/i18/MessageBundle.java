/**
 * 
 */
package com.knowledgehut.crm.i18;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * this message bundle {@code MessageBundle} class is responsible for fetching property values
 * 
 */
public class MessageBundle extends ResourceBundle {

	protected static final String BUNDLE_NAME = "com.knowledgehut.crmcrm.i18n.application";
    private static MessageBundle _instance;
	
	private MessageBundle() {
		if (null == getLocale())
			parent = ResourceBundle.getBundle(BUNDLE_NAME);
		else 
			parent = ResourceBundle.getBundle(BUNDLE_NAME, getLocale());
	}
	
	public static MessageBundle getInstance() {
		if (null == _instance) {
			_instance = new MessageBundle();
		}
		return _instance;
	}
	
	@Override
	protected Object handleGetObject(String key) {
		return parent.getObject(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return parent.getKeys();
	}
}
