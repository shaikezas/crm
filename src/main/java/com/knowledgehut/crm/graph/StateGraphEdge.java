package com.knowledgehut.crm.graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

public class StateGraphEdge{
	
	private String commandName;

    private List<String> commandNames;

	private StateGraphVertex sourceVertex;
	
	private StateGraphVertex targetVertex;

    @Deprecated
    //use the getCommandNames method instead of this
	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
        this.commandNames = parseCommandName();
	}

    private List<String> parseCommandName(){
        if(StringUtils.hasText(commandName)){
            return Arrays.asList(commandName.split(","));
        }else{
            return new ArrayList<String>();
        }
    }

    public StateGraphVertex getSourceVertex() {
		return sourceVertex;
	}

	public void setSourceVertex(StateGraphVertex sourceVertex) {
		this.sourceVertex = sourceVertex;
	}

	public StateGraphVertex getTargetVertex() {
		return targetVertex;
	}

	public void setTargetVertex(StateGraphVertex targetVertex) {
		this.targetVertex = targetVertex;
	}

    public List<String> getCommandNames() {
        return commandNames;
    }

    public void setCommandNames(List<String> commandNames) {
        this.commandNames = commandNames;
    }
    
    @Override
    public String toString() {
    	return sourceVertex+"->"+targetVertex;
    }
}
