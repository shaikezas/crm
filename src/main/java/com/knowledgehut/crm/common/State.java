package com.knowledgehut.crm.common;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

@XmlEnum
@XmlRootElement
public enum State {
	NEW(1),
	OPEN(2),
	ASSIGNED(3),
	CANCELLED(4),
	CLOSED(5);
	
	
	private static Map<Long, State> stateMap = new HashMap<Long, State>();

	static{
		for(State state : State.values()){
			stateMap.put(state.stateId, state);
		}
	}
	
	private long stateId;
	
	private State(long stateId){
		this.stateId = stateId;
	}
	
	public long value(){
		return stateId;
	}
	
	public static State forValue(long value){
		return stateMap.get(value);
	}

}
