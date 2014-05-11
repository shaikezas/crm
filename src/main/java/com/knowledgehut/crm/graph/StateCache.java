package com.knowledgehut.crm.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowledgehut.crm.entities.LeadState;
import com.knowledgehut.crm.repositories.LeadStateRepository;

@Component
public class StateCache {
	private Map<Long, LeadState> substatesMap;
	private static StateCache instance;
	
	@Autowired
	private LeadStateRepository leadStateRepository;
	
	private StateCache() {
		//Private default constructor
		instance = this;
	}
	
	public static StateCache getInstance() {
		return instance;
	}
	
	/*
	 * This method initializes the cache.
	 */
	@PostConstruct
	public void initialize() {
		substatesMap = new HashMap<Long, LeadState>();

		List<LeadState> substates = leadStateRepository.findAll();
		for(LeadState subState : substates) {
			substatesMap.put(subState.getId(), subState);
		}
	}
	
	/**
	 * This method returns the SubState instance based upon the <code>substateId</code>
	 * @param substateId
	 * @return
	 */
	public LeadState getSubState(Long substateId) {
		return substatesMap.get(substateId);
	}
}