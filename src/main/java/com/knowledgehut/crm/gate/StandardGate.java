package com.knowledgehut.crm.gate;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.knowledgehut.crm.command.Command;
import com.knowledgehut.crm.command.CommandResult;

@Component
public class StandardGate implements Gate {

	@Resource
	private RunEnvironment runEnvironment;

	private static final Logger log = LoggerFactory.getLogger(StandardGate.class);

//	private static final Timer updateRequests = Metrics.newTimer(StandardGate.class,
//		"updateRequests", TimeUnit.MILLISECONDS, TimeUnit.SECONDS);

//	static {
//		ConsoleReporter.enable(5, TimeUnit.MINUTES);
//	}

	public CommandResult dispatch(Command command) {
        log.info("Received command : {}", command);
//		log.trace("Received Command in the Gate " + command);
//		final TimerContext context = updateRequests.time();
        MDC.put("CommandId", command.getCommandId());
        try {
			return runEnvironment.run(command);
		}
		finally {
//			context.stop();
            MDC.remove("CommandId");
		}
	}

}
