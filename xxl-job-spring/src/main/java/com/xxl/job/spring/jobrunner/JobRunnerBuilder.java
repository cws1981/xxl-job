package com.xxl.job.spring.jobrunner;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;

import java.lang.reflect.Method;

/**
 *
 */
public class JobRunnerBuilder {

    public static IJobHandler build(final Object targetObject, final Method targetMethod, final Class<?>[] pTypes) {

        return new IJobHandler() {

            @Override
            public ReturnT<String> execute(String s) throws Exception {
                Object resultObj;

                if (pTypes == null || pTypes.length == 0) {
                    resultObj = targetMethod.invoke(targetObject);
                }else {
                    Object[] pTypeValues = new Object[pTypes.length];
                    if (pTypes[0] == String.class){
                        pTypeValues[0] = s;
                    }
                    resultObj = targetMethod.invoke(targetObject, pTypeValues);
                }

                Class<?> returnType = targetMethod.getReturnType();
                if (returnType != ReturnT.class) {
                    return SUCCESS;
                }

                return (ReturnT)resultObj;
            }

        };
    }
}
