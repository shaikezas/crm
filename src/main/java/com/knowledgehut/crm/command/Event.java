package com.knowledgehut.crm.command;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.Collections;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Event extends EventObject {

	private static final long serialVersionUID = 5615592738219705952L;

	private String id;

	private Date eventTimeStamp;

	private Map<String, Object> properties = Collections.emptyMap();

	public Event(Object source) {
        super(source != null ? source : "NA");
		this.id = UUID.randomUUID().toString();
		this.eventTimeStamp = new Date();
	}

	public String getId() {
		return id;
	}

	public Object getProperty(String key) {
		initialize();
		return properties.get(key);
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	private void initialize() {
		if (this.properties.equals(Collections.emptyMap())) {
			this.properties = new HashMap<String, Object>();
		}
	}

	public void addProperty(String key, Object value) {
		initialize();
		properties.put(key, value);
	}

	public void addProperties(Map<String, Object> properties) {
		initialize();
		this.properties.putAll(properties);
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public Date getEventTimeStamp() {
		return eventTimeStamp;
	}
	
	public String toDisplayString() {
		return toString();
	}
}
