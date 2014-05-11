package com.knowledgehut.crm.command;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class CommandResult<R> implements Serializable {

	private static final long serialVersionUID = 467402002680367771L;

    @XmlRootElement
	public enum STATUS {
		SUCCESS,
		FAILED,
	}
	
	private STATUS status = STATUS.SUCCESS;

	private String remarks;

	private Date date = Calendar.getInstance().getTime();

    private R response;

    @XmlElement
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    @XmlElement
	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    @XmlElement
    public R getResponse() {
        return response;
    }

    public void setResponse(R response) {
        this.response = response;
    }

    public static <T> CommandResult<T> newInstance(T result) {
        CommandResult<T> commandResult = new CommandResult<T>();
        commandResult.setResponse(result);
        return commandResult;
    }
}
