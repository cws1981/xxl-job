package com.xxl.job.spring.jobrunner;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

/**
 * Created by chenwenshun on 2019/11/15.
 */
@Component
@JobHandler("xxlCommonJobHandler")
public class JobDispathcer extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {

        String jobId = null;


        try {
            JSONObject jsonObject = JSON.parseObject(s);

            jobId = jsonObject.getString("jobId");
        } catch (Exception e) {
            return new ReturnT(ReturnT.FAIL_CODE,"json parse error : [" + e.getMessage() + "]");
        }

        IJobHandler jobRunner = null;
        if (jobId != null && !"".equals(jobId)) {
            jobRunner = JobRunnerHolder.getJobRunner(jobId);
        }
        if (jobRunner == null) {

            return new ReturnT(ReturnT.FAIL_CODE,"Can not find JobRunner by jobid Value : [" + jobId + "]");
        }


        try {
            return jobRunner.execute(s);
        } catch (Exception e) {
            if (e instanceof InterruptedException) {
                throw e;
            }
            XxlJobLogger.log(e);
            return new ReturnT<String>(ReturnT.FAIL_CODE, e.getMessage());
        }

    }
}
