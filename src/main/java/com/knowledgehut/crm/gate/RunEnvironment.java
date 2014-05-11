package com.knowledgehut.crm.gate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.knowledgehut.crm.command.Command;
import com.knowledgehut.crm.command.CommandResult;
import com.knowledgehut.crm.command.Event;
import com.knowledgehut.crm.handler.CommandHandler;

@Component
public class RunEnvironment {

	@Resource
	private CommandHandlerRegistry commandHandlerRegistry;

	public CommandResult run(Command command) {
		List<? extends Event> events = new ArrayList<Event>();
		CommandResult response = execute(command, events);
		return response;
	}
	
//	@Transactional
	public <R> CommandResult<R> execute(Command<R> command, List<? extends Event> events) {
		CommandHandler<R, Command<R>> commandHandler = commandHandlerRegistry.getHandler(command);
		CommandResult<R> response = commandHandler.execute(command);
        //TODO:Raise Events
		return response;
	}

}
