package com.knowledgehut.crm.graph;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowledgehut.crm.command.StateTransitionCommand;
import com.knowledgehut.crm.common.State;
import com.knowledgehut.crm.entities.Lead;
import com.knowledgehut.crm.exception.IdempotentStateTransitionException;
import com.knowledgehut.crm.exception.StateMachineException;
import com.knowledgehut.crm.repositories.LeadRepository;


@Component
public class GraphValidationUtils {
	
    Logger logger = LoggerFactory.getLogger(GraphValidationUtils.class);
	
	@Autowired
	private LeadRepository leadRepository;
	
	@Autowired
	private StateGraph stateGraph;
	
	public void validateCommand(StateTransitionCommand command){
		Long leadId = command.getLeadId();
		Lead lead = leadRepository.findOne(leadId);
		if(lead==null){
		  throw new StateMachineException(command.getClass().getSimpleName() + " is not a valid transition for requirement :" +
          leadId + ". No requirement found. ");
		}
		Long currentState = lead.getStatus();
        StateGraphVertex stateVertex = new StateGraphVertex(State.forValue(currentState));

        Set<StateGraphEdge> outgoingEdges = stateGraph.getOutgoingEdges(stateVertex);
        for(StateGraphEdge edge : outgoingEdges){
        	if(edge.getCommandName().equals(command.getClass().getSimpleName())){
        		return;
        	}
        }
        
        Set<StateGraphEdge> incomingEdges = stateGraph.getIncomingEdges(stateVertex);
        for(StateGraphEdge edge : incomingEdges){
        	if(edge.getCommandName().equals(command.getClass().getSimpleName())){
        		logger.info("Requirement already in destination state:" + leadId);
        		throw new IdempotentStateTransitionException("Requirement already in destination state ");
        	}
        }
        
        logger.error("{} is not a valid transition for requirement :{}. Current state of the requirement is {}",
                new Object[]{command.getClass().getSimpleName(), leadId, State.forValue(currentState)});
        throw new StateMachineException(command.getClass().getSimpleName() + " is not a valid transition for requirement :" +
                leadId + ". Current state of the requirement is " + State.forValue(currentState));
    }

}