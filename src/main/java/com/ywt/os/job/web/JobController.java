package com.ywt.os.job.web;

import com.ywt.os.job.entity.Job;
import com.ywt.os.job.param.JobResponse;
import com.ywt.os.job.service.*;
import com.ywt.os.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController{

    @Autowired
    private HPFService hpfService;

    @Autowired
    private RRService rrService;

    @Autowired
    private JobFCFSService jobFcfsService;

    @Autowired
    private SJFService sjfService;

    @Autowired
    private InitTaskService initTaskService;

    @RequestMapping(value = "job/{method}",method = RequestMethod.POST)
    public ResponseMessage Job(@PathVariable("method") String method){
        List<Job> p = new ArrayList<>();
        JobResponse jobResponses = new JobResponse();
        if(method.equals("HRRN")){
            initTaskService.initTask(p,jobResponses);
            //最高优先权算法
            return ResponseMessage.newOkInstance(this.hpfService.method(p,jobResponses));
        }else if(method.equals("RR")){
            initTaskService.initTask(p,jobResponses);
            //时间片轮转调度算法
            return ResponseMessage.newOkInstance(this.rrService.method(p,jobResponses));
        }else if(method.equals("FCFS")){
            jobFcfsService.initTask(p,jobResponses);
            //先来先服务算法
            return ResponseMessage.newOkInstance(this.jobFcfsService.method(p,jobResponses));
        }else if(method.equals("SJF")){
            //sjfService.initTask(p,jobResponses);
            initTaskService.initTask(p,jobResponses);
            jobResponses.setJob(null);
            //短作业优先算法
            return ResponseMessage.newOkInstance(this.sjfService.method(p,jobResponses));
        }else{
            return null;
        }
    }
}
