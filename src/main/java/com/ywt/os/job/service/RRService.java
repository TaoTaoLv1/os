package com.ywt.os.job.service;

import com.ywt.os.job.common.Common;
import com.ywt.os.job.entity.Job;
import com.ywt.os.job.param.JobResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 时间片轮转调度算法
 */
@Service
public class RRService implements MethodService{


    @Override
    public JobResponse method(List<Job> Jobs, JobResponse jobResponses) {
        List<Job> temp = new ArrayList<>();
        int tem = 0;
        while (true){
            for (int i = 0; i < Jobs.size(); i++) {
                if (Jobs.get(i).getServiceTime() <= Common.Circle_size) {
                    Job Job = Jobs.get(i);
                    double nowTime = System.nanoTime();
                    double time = (nowTime-Jobs.get(i).getStartTime())/1000000000;
                    Jobs.get(i).setWholeTime(time);
                    Job.setWholeTime(Jobs.get(i).getWholeTime());
                    Jobs.get(i).setWeightWholeTime(Jobs.get(i).getWholeTime()/Jobs.get(i).getArrivalTime());
                    Job.setWeightWholeTime(Jobs.get(i).getWeightWholeTime());
                    tem++;

                    temp.add(Job);
                    Jobs.remove(Job);
                    i--;
                } else {
                    Jobs.get(i).setServiceTime(Jobs.get(i).getServiceTime() - Common.Circle_size);
                }
            }
            if (Jobs.size()==0){
                break;
            }
        }
        jobResponses.setJob(temp);
        return jobResponses;
    }
}
