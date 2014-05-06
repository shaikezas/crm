package com.knowledgehut.crm.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class SpringUtils {
    private static final Logger log = LoggerFactory.getLogger(SpringUtils.class);

    private ApplicationContext applicationContext;
    private static SpringUtils springUtils;
    private static Map<String, JpaRepository> repos = new HashMap<String, JpaRepository>();

    @Autowired
    public SpringUtils(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        springUtils = this;
    }

    private static AutowireCapableBeanFactory getBeanFactory() {
        return currentInstance().applicationContext.getAutowireCapableBeanFactory();
    }

    public static Object getBean(String beanRef) {
        return getBeanFactory().getBean(beanRef);
    }

    public static <R extends JpaRepository> R getRepositoryFor(Class<? extends R> domain) {

        Object bean = currentInstance().applicationContext.getBean(domain);

        if (repos.isEmpty()) {
            repos = currentInstance().applicationContext.getBeansOfType(JpaRepository.class);
        }
        if (repos == null) {
            log.error("No repository found!!");
        }

        JpaRepository targetRepository = null;

        for (String key : repos.keySet()) {
            targetRepository = matchRepositoryByMethod(repos.get(key));
            // targetRepository = matchRepositoryByField(domain,
            // repos.get(key));
            if (targetRepository != null) {
                repos.put(key, targetRepository);
                break;
            }
        }
        if (targetRepository == null) {
            log.error("No repository found for " + domain);
            return null;
        }
        return (R) targetRepository;
    }

    public static <R extends JpaRepository> R getRepository(Class<? extends R> repositoryType) {
        R repository = currentInstance().applicationContext.getBean(repositoryType);
        return matchRepositoryByMethod(repository);
    }

    private static <R extends JpaRepository> R matchRepositoryByMethod(R jpaRepository) {
        Method method = ReflectionUtils.findMethod(SimpleJpaRepository.class, "getDomainClass");
        method.setAccessible(true);
        Class repoOfClazz = null;
        if (Proxy.isProxyClass(jpaRepository.getClass())) {
            Class zuper = jpaRepository.getClass();
            while (zuper != Object.class) {
                zuper = zuper.getSuperclass();
                System.out.println(zuper);
            }

            for (Class interfazes : jpaRepository.getClass().getInterfaces()) {
                System.out.println(interfazes);
            }

            if (jpaRepository instanceof Advised) {
                try {
                    R match = matchRepositoryByMethod((R) ((Advised) jpaRepository).getTargetSource().getTarget());
                    if (match != null) {
                        return jpaRepository;
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return null;
                }
            }

            System.out.println(jpaRepository.getClass().getSuperclass());
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(jpaRepository);
            try {
                repoOfClazz = (Class) invocationHandler.invoke(jpaRepository, method, null);
                // repoOfClazz = (Class) ReflectionUtils.invokeMethod(method,
                // jpaRepository);
            } catch (Throwable e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (jpaRepository instanceof SimpleJpaRepository) {
            repoOfClazz = (Class) ReflectionUtils.invokeMethod(method, jpaRepository);
        }

        if (repoOfClazz != null) {
            return jpaRepository;
        }
        return null;
    }

    public static SpringUtils currentInstance() {
        if (springUtils == null) {
            log.error("springUtils singleton is null!!");
        }
        return springUtils;
    }
}
