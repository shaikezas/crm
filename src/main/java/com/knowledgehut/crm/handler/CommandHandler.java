package com.knowledgehut.crm.handler;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knowledgehut.crm.command.Command;
import com.knowledgehut.crm.command.CommandResult;
import com.knowledgehut.crm.exception.StateMachineException;

public abstract class CommandHandler<R, C extends Command<R>> {

    protected Logger log = LoggerFactory.getLogger(getClass());

	@Inject
	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public void validate(C command) {
		Set<ConstraintViolation<C>> constraintViolations = validator.validate(command);
		if (constraintViolations.size() > 0) {
			StringBuilder errMsg = new StringBuilder();
			errMsg.append("[");
			for (ConstraintViolation<C> vio : constraintViolations) {
				errMsg.append(vio.getPropertyPath() + ":" + vio.getMessage() + ",");
			}
			errMsg.append("]");
			throw new RuntimeException(errMsg.toString());
		}
        validateSpecific(command);
	}

	public abstract void validateSpecific(C command);
	
	/**
	 * @param command
	 * @return to state to which the suborder has moved.
	 * @throws StateMachineException
	 */
	public abstract CommandResult<R> execute(C command);
	
}
