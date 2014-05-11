package com.knowledgehut.crm.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.knowledgehut.crm.command.CommandResponse;
import com.knowledgehut.crm.command.CommandResult;
import com.knowledgehut.crm.command.StateTransitionCommand;
import com.knowledgehut.crm.common.State;
import com.knowledgehut.crm.exception.InvalidTransitionException;
import com.knowledgehut.crm.graph.GraphValidationUtils;
import com.knowledgehut.crm.graph.StateTransitionUtils;
import com.knowledgehut.crm.operation.StateTransitionOperation;
import com.knowledgehut.crm.operation.StateTransitionOperationRegistry;
import com.knowledgehut.crm.repositories.LeadRepository;
import com.knowledgehut.crm.repositories.LeadTransitionRepository;


public abstract class StateTransitionCommandHandler <C extends StateTransitionCommand> extends CommandHandler<CommandResponse, C> {
	
@Autowired
	protected LeadTransitionRepository leadTransitionLogRepo;
	
	@Autowired
	protected LeadRepository leadRepository;
	
	@Autowired
	protected StateTransitionUtils stateTransitionUtils;
	
	
	@Autowired
	private StateTransitionOperationRegistry registry;
	
	
	@Autowired
  private GraphValidationUtils graphValidationUtils;
	
	protected static Logger log = LoggerFactory.getLogger(StateTransitionCommandHandler.class);
	
	public CommandResult<CommandResponse> execute(C command) {
	 
	  CommandResponse response = new CommandResponse();
    CommandResult<CommandResponse> result = new CommandResult<CommandResponse>();
    result.setResponse(response);
    validate(command);
    State toState = getToState(command);
    StateTransitionOperation operation = getOperation(command, toState);
    if(operation!=null){
        operation.execute(command);
        response.setReturnIdentifier(operation.getStlId());
        result.setStatus(CommandResult.STATUS.SUCCESS);
    }else{
      result.setStatus(CommandResult.STATUS.FAILED);
    }
    
    return result;
	};
	
	/**
   * This method needs to be implemented by OrderStateTransitionCommandHandler and SuborderStateTransitionCommandHandler in order to get EdgeOperation.
   * @param command
   * @param toState
   * @return StateTransitionOperation corresponding to the currentState of the order/suborder and the toState to be achieved
   * @throws InvalidTransitionException
   */
	protected StateTransitionOperation getOperation(C command, State toState) throws InvalidTransitionException{
    State currentState = State.forValue(leadRepository.findOne(command.getLeadId()).getStatus());
    log.info("current state of Requirement :"+command.getLeadId()+" is "+currentState.toString()+" and will be moved to "+toState.toString());
    return registry.getStateTransitionOperation(currentState, toState, command);
  }
  
	
//	protected abstract void executePreDbUpdate(T command, RequirementStateTransitionLog stl, Requirement requirement,  List<? extends Event> events) throws StateMachineException;
	
//	protected abstract void executePostDbUpdate(T command, RequirementStateTransitionLog stl, Requirement requirement,  List<? extends Event> events) throws StateMachineException;
		
	
	public void validate(C command){
	  super.validate(command);
	  graphValidationUtils.validateCommand(command);
	  validateSpecific(command);
	}

	public abstract void validateSpecific(C command);
	
	public State getToState(C command) throws InvalidTransitionException {
	  List<State> reachableToStates= stateTransitionUtils.findPossibleToStates(command);
    if(reachableToStates.size()==1){
      return reachableToStates.get(0);
    }
    else{
      State toState = getToStateSpecific(command, reachableToStates);
      if(toState!=null){
        return toState;
      }
      else{
        throw new InvalidTransitionException("Invalid State Transition for requirement: "+ command.getLeadId());
      }
    }
  }
	
	
	protected State getToStateSpecific(C command, List<State> states) throws InvalidTransitionException {
		return null;
	}
}