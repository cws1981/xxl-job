package com.xxl.job.spring.jobrunner;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * Created by chenwenshun on 2019/11/15.
 */
public class TaskScanner implements InitializingBean,ApplicationContextAware {

    private ApplicationContext applicationContext;
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        Class<?> clazz = bean.getClass();
//
//
//        if(!clazz.isAnnotationPresent(JobRunner.class)) {
//            return bean;
//        }
//
//
//        JobRunnerHolder.addJobBean(bean);
//
//        return bean;
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> jobRunnerMap = applicationContext.getBeansWithAnnotation(JobRunner.class);
        if (jobRunnerMap != null && !jobRunnerMap.isEmpty() ){
            for (Object o : jobRunnerMap.values()) {
                JobRunnerHolder.addJobBean(o);
            }
        }


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
