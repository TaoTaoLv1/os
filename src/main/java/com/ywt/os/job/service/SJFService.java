package com.ywt.os.job.service;

import com.ywt.os.job.common.Common;
import com.ywt.os.job.entity.Job;
import com.ywt.os.job.param.JobResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 短作业优先算法
 */
@Service
public class SJFService implements MethodService {

    @Override
    public JobResponse method(List<Job> Jobs, JobResponse jobResponses) {
        Collections.sort(Jobs, (o1, o2) -> {
            int flag = Common.compareJob(o1, o2);
            return flag;
        });

        for (int i = 0; i < Common.task_num; i++) {
            jobResponses.setJob(Jobs);
            double nowTime = System.nanoTime();
            double time = (nowTime - Jobs.get(i).getStartTime()) / 1000000000;
            Jobs.get(i).setWholeTime(time);
            jobResponses.getJob().get(i).setWholeTime(time);
            Jobs.get(i).setWeightWholeTime(Jobs.get(i).getWholeTime() / Jobs.get(i).getArrivalTime());
            jobResponses.getJob().get(i).setWeightWholeTime(Jobs.get(i).getWeightWholeTime());
        }
        return jobResponses;
    }
}
