package com.ywt.os.job.service;

import com.ywt.os.job.common.Common;
import com.ywt.os.job.entity.Job;
import com.ywt.os.job.param.JobResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 先来先执行算法
 */
@Service
public class JobFCFSService implements MethodService {

    @Autowired
    private InitTaskService initTaskService;

    public void initTask(List<Job> Jobs, JobResponse jobResponses) {

        initTaskService.initTask(Jobs, jobResponses);
        List list = Arrays.asList(Common.arr);
        Collections.shuffle(list);
        jobResponses.setList(list);
        int i = 0;
        for (Job p : Jobs) {
            if (i < Common.task_num) {
                p.setPid(Integer.parseInt((String) list.get(i)));
                jobResponses.getJob().get(i).setPid(p.getPid());
                i++;
            }
        }
    }

    @Override
    public JobResponse method(List<Job> Jobs, JobResponse jobResponses) {
        Job[] Job = new Job[Common.task_num];
        Jobs.toArray(Job);

        for (int i = 0; i < Common.task_num; i++) {
            double nowTime = System.nanoTime();
            double time = (nowTime - Jobs.get(i).getStartTime()) / 1000000000;
            Jobs.get(i).setWholeTime(time);
            jobResponses.getJob().get(i).setWholeTime(time);
            Jobs.get(i).setWeightWholeTime(Jobs.get(i).getWholeTime() / Jobs.get(i).getArrivalTime());
            jobResponses.getJob().get(i).setWeightWholeTime(Jobs.get(i).getWholeTime() / Jobs.get(i).getArrivalTime());

        }
        return jobResponses;
    }
}
