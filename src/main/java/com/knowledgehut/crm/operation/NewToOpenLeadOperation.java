package com.knowledgehut.crm.operation;

import org.springframework.stereotype.Component;

import com.knowledgehut.crm.command.CreateLeadCommand;
import com.knowledgehut.crm.common.State;
import com.knowledgehut.crm.entities.Lead;
import com.knowledgehut.crm.entities.LeadTransition;

@Component
public class NewToOpenLeadOperation extends StateTransitionOperation<CreateLeadCommand> {


  @Override
  public void register() {
    StateTransitionOperationRegistry.register(State.NEW, getToState(), this);
  }

  @Override
  protected State getToState() {
    return State.OPEN;
  }

  @Override
  protected void executeSpecific(CreateLeadCommand command, LeadTransition stl, Lead lead) {
    
  }

}
