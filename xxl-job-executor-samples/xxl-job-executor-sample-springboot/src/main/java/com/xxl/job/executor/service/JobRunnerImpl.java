package com.xxl.job.executor.service;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.spring.jobrunner.JobRunner;
import com.xxl.job.spring.jobrunner.JobRunnerItem;
import org.springframework.stereotype.Component;


@JobRunner
@Component
public class JobRunnerImpl {



    @JobRunnerItem(jobId = "111")
    public ReturnT<String> runJob1(String job) throws Throwable {
        try {
            Thread.sleep(1000L);


            XxlJobLogger.log("runJob1 我要执行：" + job);

            XxlJobLogger.log("测试，业务日志啊啊啊啊啊");

        } catch (Exception e) {
            XxlJobLogger.log( e);
            return new ReturnT(ReturnT.FAIL_CODE, e.getMessage());
        }
        return new ReturnT(ReturnT.SUCCESS_CODE, "执行成功了，哈哈");
    }

    @JobRunnerItem(jobId = "222888")
    public void runJob2(String jobContext) throws Throwable {

            XxlJobLogger.log("runJob2 我要执行");
            XxlJobLogger.log("测试，业务日志啊啊啊啊啊");
    }

}
