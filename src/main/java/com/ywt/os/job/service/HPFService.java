package com.ywt.os.job.service;

import com.ywt.os.job.common.Common;
import com.ywt.os.job.entity.Job;
import com.ywt.os.job.param.JobResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 最高优先级算法
 */
@Service
public class HPFService implements MethodService {

    public static Job getTask(List<Job> Jobs, JobResponse jobResponse) {//根据响应比，返回一个最高相应比进程
        Job Job = new Job();
        int temp = 0;
        double maxRadio = 0;
        for (int i = 0; i < Jobs.size(); i++) {
            if (Jobs.get(i).getRadio() > maxRadio) {
                maxRadio = Jobs.get(i).getRadio();
                Job = Jobs.get(i);
                temp = i;
            }
        }
        System.out.print("进程号为：" + Job.getPid() + "的响应比最高，为：" + Job.getRadio() + "------");
        Jobs.remove(Jobs.get(temp));
        return Job;

    }

    public static void getRadio(List<Job> Jobs, JobResponse jobResponses)//计算每一个进程当前的响应比
    {

        double[] radio = new double[Jobs.size()];
        for (int i = 0; i < Jobs.size(); i++) {
            //响应比=（等待时间+服务时间）/服务时间
            radio[i] = ((System.nanoTime() - Jobs.get(i).getStartTime()) + Jobs.get(i).getServiceTime()) / Jobs.get
                    (i).getServiceTime();
            Jobs.get(i).setRadio(radio[i]);
            jobResponses.getJob().get(i).setRadio(radio[i]);

        }
    }

    @Override
    public JobResponse method(List<Job> Jobs, JobResponse jobResponses) {
        List<Job> temp = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < Common.task_num; i++, j++) {
            if (Jobs.size() == 0) {
                break;
            }
            getRadio(Jobs, jobResponses);
            Job tem = getTask(Jobs, jobResponses);
            double nowTime = System.nanoTime();
            double time = (nowTime - tem.getStartTime()) / 1000000000;

            tem.setWholeTime(time);
            tem.setWeightWholeTime(tem.getWholeTime() / tem.getArrivalTime());
            temp.add(tem);
            i--;

        }
        jobResponses.setJob(temp);
        return jobResponses;
    }
}
