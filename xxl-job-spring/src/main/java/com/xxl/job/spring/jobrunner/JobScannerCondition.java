package com.xxl.job.spring.jobrunner;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by chenwenshun on 2019/11/15.
 */

public class JobScannerCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//       String  enable = context.getEnvironment().getProperty("xxl.job.enable");
//       return  "true".equalsIgnoreCase(enable);
        boolean c = (context.getBeanFactory().getBean(XxlJobSpringExecutor.class)) != null;
        return c;
    }
}
