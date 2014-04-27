
package com.knowledgehut.crm.common;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Custom exception handler for handling all the exceptions.
 * 
 * @author Tanuj.Batra
 *
 */
public class CRMExceptionHandler extends ExceptionHandlerWrapper {

	private Logger logger = LoggerFactory.getLogger(CRMExceptionHandler.class);
			
	private ExceptionHandler wrapped;

	public CRMExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		//Iterate over all unhandeled exceptions
		Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
		while (i.hasNext()) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context =
					(ExceptionQueuedEventContext)event.getSource();

			Throwable t = context.getException();

			try {
				try {
					Object obj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");
					if(obj != null) {
						CurrentUser currentUser = (CurrentUser) obj;
					}
				}catch(Exception e) {
					logger.error("Exception occurred while retrieving the site id in crmRMExceptionHandler", e);
				}
				FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Error Occurred: " ));
			} finally {
				i.remove();
			}
		}
		//let the parent handle the rest
		getWrapped().handle();
	}
}