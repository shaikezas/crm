package com.knowledgehut.crm.gate;

import com.knowledgehut.crm.command.Command;
import com.knowledgehut.crm.command.CommandResult;


/**
 * Main access point to the Application.<br>
 * It handles:
 * <ul>
 * <li>filtering commands duplicates
 * <li>commands queues for asynchronous commands
 * </ul>
 * 
 *
 */
public interface Gate {

	public abstract CommandResult dispatch(Command command);

}