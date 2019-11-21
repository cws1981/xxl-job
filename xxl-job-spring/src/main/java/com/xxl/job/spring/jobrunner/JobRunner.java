package com.xxl.job.spring.jobrunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by chenwenshun on 2019/11/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Component
public @interface JobRunner {
}
