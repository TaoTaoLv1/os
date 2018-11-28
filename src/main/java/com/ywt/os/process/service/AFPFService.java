package com.ywt.os.process.service;

import com.ywt.os.process.bll.ProcessBLL;
import com.ywt.os.exception.UnknownException;
import com.ywt.os.process.entity.AFPFModel;
import com.ywt.os.process.entity.Model;
import com.ywt.os.process.param.ResponseData;
import com.ywt.os.process.web.AFPFController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author: YwT
 * @description:
 *   抢占式优先权调度算法(绝对优先权)
 *   算法可以从时间的角度来模拟
 *   即随着时间的推移，进程逐个被完成.
 *   当有一个优先更高的进程进来时，当前进程被挂起
 * @create: 2018-11-24 22:35
 **/
@Service
public class AFPFService implements ProcessSchedule {

    private static final String TAG = AFPFService.class.getSimpleName();

    @Autowired
    private AFPFController afpfController;

    @Override
    public ResponseData execute(Model... processList) throws InterruptedException {
        if (processList == null || processList.length == 0) {
            throw new NullPointerException("进程为空");
        }

        if (!(processList instanceof AFPFModel[])) {
            throw new IllegalArgumentException("数据类型出错");
        }

        ResponseData responseData = new ResponseData();
        int TTimeSum = 0;
        int TWTimeSum = 0;

        AFPFModel[] processes = (AFPFModel[]) processList;
        long[] comingTime = new long[processes.length];
        recordComingTime(processes, comingTime);
        AFPFModel currentProcess = null; // 占用CPU的进程
        int index = -1;
        long currentTime = 0; // 当前时间
        while(!isDone(processes)) {
            index = getNextIndex(processes, currentTime);
            if (index > processes.length || index < 0) {
                throw new UnknownException("未知异常");
            }

            // 针对尚未初始化和已经运行结束的进程
            if (null == currentProcess || currentProcess.getFreeTime() <= 0) {
                currentProcess = processes[index];
            }

            // 针对有抢占进程时
            if (!processes[index].getProcessId().equals(currentProcess.getProcessId())) {
                currentProcess = processes[index];
            }

            // 针对中间CPU空闲的逻辑处理
            if (currentTime < currentProcess.getComingTime()) {
                currentTime = currentProcess.getComingTime();
            }

            { // 进程被执行过程模拟
                currentProcess.reduceSelfFreeTime();
                currentTime++;
                if (currentProcess.getFreeTime() <= 0) {
                    currentProcess.setFinishTime(currentTime);
                    currentProcess.setTurnaroundTime(currentProcess.getFinishTime() - currentProcess.getComingTime());
                    currentProcess.setTurnaroundWeightTime(1.0 * currentProcess.getTurnaroundTime() / currentProcess.getTimeToNeed());

                    TTimeSum += currentProcess.getTurnaroundTime();
                    TWTimeSum += currentProcess.getTurnaroundWeightTime();

                    Thread.sleep(2000);

                    afpfController.sendAFPF(currentProcess);
                }
            }
        }

        responseData.setTimeSum(currentTime);
        responseData.setAveTurnaroundTime(TTimeSum / processes.length);
        responseData.setAveTurnaroundWeightTime(TWTimeSum / processes.length);

        return responseData;
    }

    /**
     * 获得将在下一个时间单位获得CPU的进程下标
     * @param processAarry 进程列表
     * @param currentTime 当前时间
     * @return 进程下标
     */
    private int getNextIndex(AFPFModel[] processAarry, long currentTime) {
        int index = -1;
        if (processAarry == null || processAarry.length == 0) {
            return index;
        }

        int maxPriority = Integer.MIN_VALUE;
        int earliestIndex = -1; // 未执行的最早的进程
        long earliestTime = Long.MAX_VALUE;
        int earliestMaxPriority = Integer.MIN_VALUE;
        for (int i = 0; i < processAarry.length; i++) {
            if (processAarry[i].getFreeTime() <= 0) {
                continue;
            }

            // 过滤尚未到达的进程
            if (!ProcessBLL.isProcessComing(processAarry[i], currentTime)) {
                if (earliestTime > processAarry[i].getComingTime()) {
                    earliestIndex = i;
                    earliestTime = processAarry[i].getComingTime();
                    earliestMaxPriority = processAarry[i].getPriority();
                } else if (earliestTime == processAarry[i].getComingTime()) {
                    if (earliestMaxPriority < processAarry[i].getPriority()) {
                        earliestMaxPriority = processAarry[i].getPriority();
                        earliestIndex = i;
                    }
                }
                continue;
            }

            if (maxPriority < processAarry[i].getPriority()) {
                maxPriority = processAarry[i].getPriority();
                index = i;
            }
        }
        index = index < 0 ? earliestIndex : index;
        return index;
    }

    /**
     * 是否所有的进程都执行完毕
     * @param array 进程列表
     * @return 是否执行结束
     */
    private boolean isDone(AFPFModel[] array) {
        for (AFPFModel process : array) {
            if (process.getFreeTime() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 记录下当前进程的到达时间
     * @param array 进程队列
     * @param times 进程到达时间列表
     */
    private void recordComingTime(AFPFModel[] array, long[] times) {
        if (times == null || times.length == 0) {
            times = new long[array.length];
        }
        for (int i = 0; i < array.length; i++) {
            times[i] = array[i].getComingTime();
        }
        Arrays.sort(times);
    }
}
