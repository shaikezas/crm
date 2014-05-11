package com.knowledgehut.crm.graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.BellmanFordShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.knowledgehut.crm.common.State;
import com.knowledgehut.crm.entities.LeadStateTransition;
import com.knowledgehut.crm.repositories.LeadStateTransitioinRepository;
import com.knowledgehut.crm.repositories.LeadTransitionRepository;



@Component
public class StateGraph {
	
	static final Logger logger = LoggerFactory.getLogger(StateGraph.class);
	
	@Resource
	private LeadStateTransitioinRepository leadStateTransitionRepository;
	
	private static DirectedGraph<StateGraphVertex, StateGraphEdge> graph=null;
	
	private static HashMap<State, StateGraphVertex> map = new HashMap<State, StateGraphVertex>();
	
	@SuppressWarnings("unused")
	@PostConstruct
	private void initialize(){
		graph = new DefaultDirectedGraph<StateGraphVertex, StateGraphEdge>(StateGraphEdge.class);
		StateGraphVertex v1,v2;
		StateGraphEdge e;
		
		List<LeadStateTransition> stateTransitionList = leadStateTransitionRepository.findAll();
		for(LeadStateTransition currentStateTransition : stateTransitionList){
			if(!currentStateTransition.getDeleted()){
				try{
					State fromState = State.forValue(currentStateTransition.getFromState().getId());
					State toState = State.forValue(currentStateTransition.getToState().getId());
					v1 = new StateGraphVertex(fromState);
					v2 = new StateGraphVertex(toState);
					if(graph.addVertex(v1)){
						map.put(fromState, v1);
					}
					if(graph.addVertex(v2)){
						map.put(toState, v2);
					}
					e = this.addEdge(v1, v2);
					if(e!=null){
						String commandName = currentStateTransition.getCommandName();
						e.setCommandName(commandName!=null ? commandName.trim() : "");
					}
				}catch(Exception ex){
					logger.info("Exception occurred while adding edge: "+ currentStateTransition.getFromState().getId()+"-->"+currentStateTransition.getToState().getId(), ex);
				}
			}
		}
		if(logger.isDebugEnabled()){
			printGraph();
		}
	}
	
	public boolean isReachable(StateGraphVertex startVertex, StateGraphVertex endVertex){
		try{
			if(BellmanFordShortestPath.findPathBetween(graph, startVertex, endVertex)!=null){
				if(logger.isDebugEnabled()){
					printPath(startVertex, endVertex);
				}
				return true;
			}
		}
		catch(IllegalArgumentException e){
			logger.error(e.getMessage(),e);
		}
		return false;
	}
	
	private void printPath(StateGraphVertex startVertex, StateGraphVertex endVertex){
		List<StateGraphEdge> edges = BellmanFordShortestPath.findPathBetween(graph, startVertex, endVertex);
		for(StateGraphEdge e : edges){
			logger.debug(String.format("\"%s\" -> \"%s\" (%s)", graph.getEdgeSource(e), graph.getEdgeTarget(e), e.getCommandName()));		
		}
	}
	
	private void printGraph(){
		Set<StateGraphEdge> edges = graph.edgeSet();
        for (StateGraphEdge e : edges) {
                logger.debug(String.format("\"%s\" -> \"%s\" (%s)", graph.getEdgeSource(e), graph.getEdgeTarget(e), e.getCommandName()));                 
        }
	}
	
	public boolean isNextVertex(StateGraphVertex fromVertex, StateGraphVertex toVertex){
		Set<StateGraphEdge> outgoingEdges = graph.outgoingEdgesOf(fromVertex);
		if(outgoingEdges==null) return false;
		for(StateGraphEdge e : outgoingEdges){
			if(graph.getEdgeTarget(e).equals(toVertex)){
				return true;
			}
		}
		return false;
	}
	
	public List<StateGraphVertex> getNextVertices(StateGraphVertex fromVertex){
		List<StateGraphVertex> nextVertices = new ArrayList<StateGraphVertex>();
		Set<StateGraphEdge> outgoingEdges = graph.outgoingEdgesOf(fromVertex);
        if (outgoingEdges != null) {
            for(StateGraphEdge e : outgoingEdges){
                nextVertices.add(graph.getEdgeTarget(e));
            }
        }
		return nextVertices;
	}
	
	public Set<StateGraphVertex> getAllVertices(){
		return graph.vertexSet();
	}
	
	public List<StateGraphVertex> getPreviousVertices(StateGraphVertex toVertex){
		List<StateGraphVertex> previousVertices = new ArrayList<StateGraphVertex>();
		Set<StateGraphEdge> incomingEdges = graph.incomingEdgesOf(toVertex);
        if (incomingEdges != null) {
            for(StateGraphEdge e : incomingEdges){
                previousVertices.add(graph.getEdgeSource(e));
            }
        }
		return previousVertices;
	}
	
	
	public Set<StateGraphEdge> getIncomingEdges(StateGraphVertex vertex) {
		Set<StateGraphEdge> incomingEdges = graph.incomingEdgesOf(vertex);
		return incomingEdges;
	}
	
	private StateGraphEdge addEdge(StateGraphVertex sourceVertex, StateGraphVertex targetVertex){
		StateGraphEdge edge = graph.addEdge(sourceVertex, targetVertex);
		if(edge!=null){
			edge.setSourceVertex(sourceVertex);
			edge.setTargetVertex(targetVertex);
		} 
		return edge;
	}
	
	public StateGraphVertex getVertex(State state){
		return map.get(state);
	}
	
	public Set<StateGraphEdge> getOutgoingEdges(StateGraphVertex vertex){
		Set<StateGraphEdge> outgoingEdges = graph.outgoingEdgesOf(vertex);
		return outgoingEdges;
	}

	
}
