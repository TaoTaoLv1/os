package com.ywt.os.process.service;

import com.ywt.os.bll.ProcessBLL;
import com.ywt.os.exception.UnknownException;
import com.ywt.os.process.entity.Model;
import com.ywt.os.process.entity.RFPFModel;
import com.ywt.os.process.param.ResponseData;
import com.ywt.os.process.web.RFPFController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: YwT
 * @description: 非抢占式优先权算法(相对优先权)
 * @create: 2018-11-24 22:17
 **/
@Service
public class RFPFService implements ProcessSchedule {

    private static final String TAG = RFPFService.class.getSimpleName();

    @Autowired
    private RFPFController rfpfController;

    @Override
    public ResponseData execute(Model... processList) {
        if (processList == null || processList.length == 0) {
            throw new NullPointerException("进程为空");
        }

        if (!(processList instanceof RFPFModel[])) {
            throw new IllegalArgumentException("数据类型出错");
        }

        ResponseData responseData = new ResponseData();
        int TTimeSum = 0;
        int TWTimeSum = 0;

        RFPFModel[] processes = (RFPFModel[])processList;
        boolean[] runFlag = new boolean[processes.length];
        int currentTime = 0;
        int index = -1;
        RFPFModel currentProcess;
        for (int i = 0; i < processes.length; i++) {
            index = getIndexComingMaxPriority(processes, runFlag, currentTime);
            if (0 <= index && index <= processes.length) {
                currentProcess = processes[index];
                if (currentTime < currentProcess.getComingTime()) {
                    currentTime = (int)currentProcess.getComingTime();
                }

                currentProcess.setStartRunTime(currentTime);
                currentTime += currentProcess.getTimeToNeed();
                currentProcess.setFinishTime(currentTime);
                currentProcess.setTurnaroundTime(currentTime - currentProcess.getComingTime());
                currentProcess.setTurnaroundWeightTime(1.0 * currentProcess.getTurnaroundTime() / currentProcess.getTimeToNeed());

                TTimeSum += currentProcess.getTurnaroundTime();
                TWTimeSum += currentProcess.getTurnaroundWeightTime();

                runFlag[index] = true;

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rfpfController.sendRFPF(currentProcess);
            } else {
                throw new UnknownException("未知异常");
            }
        }

        responseData.setTimeSum(currentTime);
        responseData.setAveTurnaroundTime(TTimeSum / processes.length);
        responseData.setAveTurnaroundWeightTime(TWTimeSum / processes.length);

        return responseData;
    }

    /**
     * 获得队列中已经到达、尚未执行且优先权最大的进程下标
     * 如果在尚未执行的进程队列中，所有的都未到达，就取最先到达的
     * @param processes 进程队列
     * @param runFlag 进程执行标志
     * @param currentTime 当前时间
     * @return 进程下标
     */
    private int getIndexComingMaxPriority(RFPFModel[] processes, boolean[] runFlag, int currentTime) {
        int maxPriority = Integer.MIN_VALUE;
        int maxPriorityIndex = -1;
        int earliestIndex = -1; // 未执行的最早的进程
        long earliestTime = Long.MAX_VALUE;
        RFPFModel currentProcess;
        for (int i = 0; i < processes.length; i++) {
            if (runFlag[i]) {
                continue;
            }

            currentProcess = processes[i];
            if (!ProcessBLL.isProcessComing(currentProcess, currentTime)) {
                if (earliestTime > currentProcess.getComingTime()) {
                    earliestIndex = i;
                    earliestTime = currentProcess.getComingTime();
                }
                continue;
            }

            if (currentProcess.getPriority() > maxPriority) {
                maxPriority = currentProcess.getPriority();
                maxPriorityIndex = i;
            }
        }

        maxPriorityIndex = maxPriorityIndex < 0 ? earliestIndex : maxPriorityIndex;

        return maxPriorityIndex;
    }
}
