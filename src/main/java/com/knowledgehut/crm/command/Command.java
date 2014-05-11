package com.knowledgehut.crm.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public abstract class Command<R> implements Serializable {

	private static final long serialVersionUID = 7348319113618399500L;

	Date date;

	private String commandId = UUID.randomUUID().toString();

	private boolean dryRun = false;

    private Long user;

    private List<Event> events = Collections.emptyList();

    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    @XmlElement
	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

    @XmlElement
	public boolean isDryRun() {
		return dryRun;
	}

	public void setDryRun(boolean isDryRun) {
		this.dryRun = isDryRun;
	}

    @XmlElement
    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    @XmlTransient
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        if (events.isEmpty()) {
            events = new ArrayList<Event>();
        }
        events.add(event);
    }

    public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}



}
