/**
 * 
 */
package com.knowledgehut.crm.graph;

import com.knowledgehut.crm.common.State;

public class StateGraphVertex {
	private State state;
	
	public StateGraphVertex(State state){
		this.state=state;
	}
	
	public Long getStateId(){
		return state.value();
	}
	
	public State getState(){
    return state;
  }
  
	
	
	public String toString(){
		return state.value() + "(" + state.toString() + ")";
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj instanceof StateGraphVertex){
			return this.state.name().equals(((StateGraphVertex)obj).state.name());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (int)state.name().hashCode();
	}
}
