package com.knowledgehut.crm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowledgehut.crm.command.StateTransitionCommand;
import com.knowledgehut.crm.common.State;
import com.knowledgehut.crm.entities.Lead;
import com.knowledgehut.crm.repositories.LeadRepository;

@Component
public class StateTransitionUtils {
	
	Logger logger = LoggerFactory.getLogger(StateTransitionUtils.class);

	@Autowired
	private LeadRepository leadRepository;
	
	@Autowired
	private StateGraph stateGraph;
	
	/**
	 * @param requirementId
	 * @param state
	 * @return true if the given requirement lies after the passed state strictly
	 */
	public boolean isAfter(Lead lead, State state) {
		return isAfter(lead, state, true);
	}

	/**
	 * overloaded method for strict check on requirement if its after the given state.
	 * @param requirementId
	 * @param state
	 * @param strict
	 * @return
	 */
	public boolean isAfter(Lead lead, State state, boolean strict) {
		//SubOrderDetail requirement = subOrderDetailRepository.findOne(requirementId);
		Long requirementState = lead.getStatus();
		if (state.value() == requirementState) {
			if(strict) // strictly isAfter
				return false;
			else
				return true;
		}
		StateGraphVertex startVertex = new StateGraphVertex(state);
		StateGraphVertex endVertex = new StateGraphVertex(State.forValue(requirementState));
		return stateGraph.isReachable(startVertex, endVertex);
	}

	/**
	 * @param requirementId
	 * @param state
	 * @return true if the given requirement lies before the passed state
	 */
	public boolean isBefore(Long requirementId, State state) {
		Lead lead = leadRepository.findOne(requirementId);
		Long requirementState = lead.getStatus();
		if (state.value() == requirementState) {
			return false;
		}
		StateGraphVertex startVertex = new StateGraphVertex(State.forValue(requirementState));
		StateGraphVertex endVertex = new StateGraphVertex(state);
		return stateGraph.isReachable(startVertex, endVertex);
	}

	
	public List<State> findPossibleToStates(StateTransitionCommand command) {
	  Long leadId = command.getLeadId();
    String commandName = command.getClass().getSimpleName();
    Lead lead = leadRepository.findOne(leadId);
    State currentState = State.forValue(lead.getStatus());
    StateGraphVertex vertex = new StateGraphVertex(currentState);
    Set<StateGraphEdge> outgoingEdges = stateGraph.getOutgoingEdges(vertex);
    List<State> toStates = new ArrayList<State>();
    for(StateGraphEdge e : outgoingEdges) {
        if(e.getCommandName().equals(commandName)) {
          toStates.add(e.getTargetVertex().getState());
        }
    }
      return toStates;
	}
	
	public Map<Long, String> findPossibleToStates(State fromState) {
		Map<Long, String> possibleToStateMap = new HashMap<Long, String>();
		
		StateGraphVertex fromVertex = new StateGraphVertex(fromState);
		List<StateGraphVertex> nextVertices = stateGraph.getNextVertices(fromVertex);
		
		for (StateGraphVertex vertex : nextVertices) {
			possibleToStateMap.put(vertex.getStateId(), State.forValue(vertex.getStateId()).name());
		}
		return possibleToStateMap;
	}
}