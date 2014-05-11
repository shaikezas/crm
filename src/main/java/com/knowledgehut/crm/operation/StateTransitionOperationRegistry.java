package com.knowledgehut.crm.operation;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knowledgehut.crm.command.Command;
import com.knowledgehut.crm.common.State;


@Component
public class StateTransitionOperationRegistry {

  private static final Map<String, StateTransitionOperation> transitionOpsRegistry =
      new HashMap<String, StateTransitionOperation>();
  
  protected static Logger LOG = LoggerFactory.getLogger(StateTransitionOperationRegistry.class);

  private static final String SEPERATOR = "|";

  @Autowired
  private List<StateTransitionOperation> operations;

  @PostConstruct
  public void registerOperations() {
    for (StateTransitionOperation<?> ops : operations) {
      ops.register();
    }
  }

  public static void register(State from, State to, StateTransitionOperation operation) {
    ParameterizedType type = (ParameterizedType) operation.getClass().getGenericSuperclass();
    String commandName = ((Class<?>) type.getActualTypeArguments()[0]).getSimpleName();
    LOG.info("Loading operation : "+from.value() + SEPERATOR + to.value() + commandName);
    
    transitionOpsRegistry.put(from.value() + SEPERATOR + to.value() + commandName, operation);
  }

  public  StateTransitionOperation getStateTransitionOperation(State from, State to, Command command) {
    return transitionOpsRegistry.get(from.value() + SEPERATOR + to.value() + command.getClass().getSimpleName());
  }

}
