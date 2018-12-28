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
        //按照服务时间长短升序排序所有进程
        Collections.sort(Jobs, (o1, o2) -> {
            int flag = Common.compareJob(o1, o2);
            return flag;
        });

        for (int i = 0; i < Common.task_num; i++) {
            System.out.print(Common.tm.format(new Date()) + "第" + (int) Jobs.get(i).getPid() + "号进程开始运行==(R)==");
//            try {
//                Thread.sleep((long) Jobs.get(i).getServiceTime() * 1000);//模拟进程执行所需要的时间
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.print(Common.tm.format(new Date()) + "进程结束运行(F)=====用时为" + (int) Jobs.get(i).getServiceTime()
                    + "s-----");
            jobResponses.setJob(Jobs);

            //获取间隔时间
            double nowTime = System.nanoTime();
            double time = (nowTime - Jobs.get(i).getStartTime()) / 1000000000;
            Jobs.get(i).setWholeTime(time);
            jobResponses.getJob().get(i).setWholeTime(time);
            System.out.print("周转时间为：" + Jobs.get(i).getWholeTime() + "S");
            Jobs.get(i).setWeightWholeTime(Jobs.get(i).getWholeTime() / Jobs.get(i).getArrivalTime());
            jobResponses.getJob().get(i).setWeightWholeTime(Jobs.get(i).getWeightWholeTime());
            System.out.println("带权周转时间为：" + Jobs.get(i).getWholeTime() / Jobs.get(i).getArrivalTime() + "S");
        }
        return jobResponses;
    }
}
