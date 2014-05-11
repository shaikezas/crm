package com.knowledgehut.crm.gate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.knowledgehut.crm.command.Command;
import com.knowledgehut.crm.handler.CommandHandler;

@Component
public class CommandHandlerRegistry implements ApplicationListener<ContextRefreshedEvent> {

    @Inject
    private ConfigurableListableBeanFactory beanFactory;

    private Map<Class<?>, String> handlers = new HashMap<Class<?>, String>();

    public <R> CommandHandler<R, Command<R>> getHandler(Command<R> command) {
        String beanName = handlers.get(command.getClass());
        if (beanName == null) {
            throw new RuntimeException("command handler not found. Command class is " + command.getClass());
        }
        CommandHandler<R, Command<R>> handler = beanFactory.getBean(beanName, CommandHandler.class);
        return handler;
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        handlers.clear();
        String[] commandHandlersNames = beanFactory.getBeanNamesForType(CommandHandler.class);
        for (String beanName : commandHandlersNames) {
            BeanDefinition commandHandler = beanFactory.getBeanDefinition(beanName);
            try {
                Class<?> handlerClass = Class.forName(commandHandler.getBeanClassName());
                handlers.put(getHandledCommandType(handlerClass), beanName);
            }
            catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Class<?> getHandledCommandType(Class<?> clazz) {
        Type[] superClassGenerics = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
        for (Type superClassGeneric : superClassGenerics) {
            if (superClassGeneric instanceof Class) {
                if (Command.class.isAssignableFrom((Class)superClassGeneric)) {
                    return (Class)superClassGeneric;
                }
            }
        }
        return null;
    }

    private Class<?> getGenericType(ParameterizedType type) {
        return (Class<?>) type.getActualTypeArguments()[0];
    }

}
