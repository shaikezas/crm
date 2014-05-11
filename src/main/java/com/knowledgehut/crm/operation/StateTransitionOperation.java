package com.knowledgehut.crm.operation;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowledgehut.crm.command.StateTransitionCommand;
import com.knowledgehut.crm.common.State;
import com.knowledgehut.crm.entities.Lead;
import com.knowledgehut.crm.entities.LeadTransition;
import com.knowledgehut.crm.exception.StateMachineException;
import com.knowledgehut.crm.repositories.LeadRepository;
import com.knowledgehut.crm.repositories.LeadStateRepository;
import com.knowledgehut.crm.repositories.LeadTransitionRepository;


public abstract class StateTransitionOperation<C extends StateTransitionCommand>  {

  protected final Logger logger = LoggerFactory.getLogger(getClass()); 
  
  @Autowired
  private LeadTransitionRepository leadTransitionLogRepository;
  
  @Autowired
  private LeadRepository leadRepository;
  
  @Autowired
  private LeadStateRepository leadStateRepository;
  
  
  /**
   * This method will be implemented by specific ReqStateTransitionOperation classes.
   * @param requirement TODO
   * @param requirementCommand
   */
  protected abstract void executeSpecific(C command, LeadTransition stl, Lead lead);
  
  protected abstract State getToState();


  public abstract void register() ;
  
  private Long stlId;
  
  public final void execute(C command) throws StateMachineException{
    LeadTransition stl = new LeadTransition();
    Lead lead = leadRepository.findOne(command.getLeadId());
    stl.setFromState(leadStateRepository.findOne(lead.getStatus()).getId());
    stl.setExternalCommunication(command.getExternalCommunication());
    stl.setInternalCommunication(command.getInternalCommunication());
    if(StringUtils.isNotBlank(command.getRemarks())){
      stl.setRemarks(command.getRemarks());
    }
    executeSpecific(command, stl, lead);
        State toState = getToState();
    lead.setStatus(toState.value());
    lead.setModifiedBy(command.getModifiedBy());
        lead = updateLead(lead);
    stl.setLeadId(lead.getId());
    stl.setToState(leadStateRepository.findOne(toState.value()).getId());
    stl.setCreatedBy(command.getModifiedBy());
    stl.setModifiedBy(command.getModifiedBy());
    
    leadTransitionLogRepository.save(stl);
    this.stlId = stl.getId();
    logger.debug("Lead  "+ command.getLeadId()+" has been updated successfully");
  }

  private Lead updateLead(Lead lead) {
    return leadRepository.save(lead);
  }

  public Long getStlId() {
    return stlId;
  }

  public void setStlId(Long stlId) {
    this.stlId = stlId;
  }

}