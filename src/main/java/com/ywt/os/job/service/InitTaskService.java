package com.ywt.os.job.service;

import com.ywt.os.job.common.Common;
import com.ywt.os.job.entity.Job;
import com.ywt.os.job.param.JobResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitTaskService {

    public void initTask(List<Job> Jobs, JobResponse jobResponses){//初始化进程列表
        List<Job> temp = new ArrayList<>();
        for(int i = 0; i< Common.task_num; i++)
        {
            Common.time = System.nanoTime();
            Job Job = new Job();
            Job.setPid(i);
            Job.setStartTime((double) System.nanoTime());
            Job.setArrivalTime((Math.random()*100)%20+1);
            Job.setServiceTime((Math.random()*100)%20+1);

            temp.add(Job);
            Jobs.add(Job);
        }
        jobResponses.setJob(temp);
    }
}
