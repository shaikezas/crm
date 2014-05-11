package com.knowledgehut.crm.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * This table holds all sub state transitions allowed for a sub order
 * 
 *
 */

@Entity
@Table(name = "LEAD_STATE_TRANSITION_MASTER")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class LeadStateTransition extends AuditableIdEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1410335048586357990L;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "FROM_STATE", referencedColumnName="ID", updatable = false)
	private LeadState fromState;
	
	@Column(name = "DELETED")
  @Type(type = "yes_no")
  private boolean isDeleted;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "TO_STATE", referencedColumnName="ID", updatable = false)
	private LeadState toState;
	
	
	@Column(name = "COMMANDNAME")
	private String commandName;
	
	

	public LeadState getFromState() {
    return fromState;
  }

  public void setFromState(LeadState fromState) {
    this.fromState = fromState;
  }

  public LeadState getToState() {
    return toState;
  }

  public void setToState(LeadState toState) {
    this.toState = toState;
  }

  public boolean getDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public String getCommandName() {
    return commandName;
  }

  public void setCommandName(String commandName) {
    this.commandName = commandName;
  }

 
}
