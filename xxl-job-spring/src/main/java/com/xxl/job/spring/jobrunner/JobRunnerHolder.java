package com.xxl.job.spring.jobrunner;


import com.xxl.job.core.handler.IJobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenwenshun on 12/21/15.
 */
public class JobRunnerHolder {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobRunnerHolder.class);

    private static final Map<String, IJobHandler> JOB_RUNNER_MAP = new ConcurrentHashMap<String, IJobHandler>();

    static void add(String shardValue, IJobHandler jobRunner) {
        JOB_RUNNER_MAP.put(shardValue, jobRunner);
    }

    public static IJobHandler getJobRunner(String shardValue) {
        return JOB_RUNNER_MAP.get(shardValue);
    }

    public static void addJobBean(Object bean) {
        Class<?> clazz = bean.getClass();
        Method[] methods = clazz.getMethods();
        if (methods != null && methods.length > 0) {
            for (final Method method : methods) {
                if (method.isAnnotationPresent(JobRunnerItem.class)) {
                    JobRunnerItem jobRunnerItem = method.getAnnotation(JobRunnerItem.class);
                    String jobId = jobRunnerItem.jobId();
                    if (jobId == null || "".equals(jobId)) {
                        LOGGER.error(clazz.getName() + ":" + method.getName() + " " + JobRunnerItem.class.getName() + " shardValue can not be null");
                        continue;
                    }
                    JobRunnerHolder.add(jobId, JobRunnerBuilder.build(bean, method, method.getParameterTypes()));
                }
            }
        }
    }
}
