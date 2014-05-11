package com.knowledgehut.crm.graph;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowledgehut.crm.common.State;
import com.knowledgehut.crm.entities.Lead;

@Component
public class StateTransitionHelper {
	
	@Autowired
	private StateGraph reqStateGraph;
	
	private static StateTransitionHelper instance;
	
	private StateTransitionHelper() {
		//Private default constructor
		instance = this;
	}
	
	public static StateTransitionHelper getInstance() {
		return instance;
	}
	
	public boolean isValidTransition(Lead lead, State toState) {
		List<State> allowedToStates = getPossibleToStates(lead);
		if(allowedToStates.contains(toState)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param requirementId
	 * @return List of next states for a given requirement and commandType
	 */
	public List<State> getPossibleToStates(Lead lead) {
		Long requirementState = lead.getStatus();
		StateGraphVertex fromVertex = new StateGraphVertex(State.forValue(requirementState));
		List<StateGraphVertex> nextVertices = reqStateGraph.getNextVertices(fromVertex);
		List<State> toStateIds = new ArrayList<State>();
		for (StateGraphVertex vertex : nextVertices) {
			toStateIds.add(State.forValue(vertex.getStateId()));
		}
		return toStateIds;
	}
}
