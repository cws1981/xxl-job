package com.xxl.job.spring.jobrunner;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by chenwenshun on 2019/11/15.
 */
public class TaskScanner implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();


        if(!clazz.isAnnotationPresent(JobRunner.class)) {
            return bean;
        }


        JobRunnerHolder.addJobBean(bean);

        return bean;
    }
}
