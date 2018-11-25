package com.ywt.os.process.service;

import com.ywt.os.bll.ProcessBLL;
import com.ywt.os.exception.UnknownException;
import com.ywt.os.process.entity.Model;
import com.ywt.os.process.entity.SPFModel;
import com.ywt.os.process.param.ResponseData;
import com.ywt.os.process.web.SPFController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: YwT
 * @description: 短进程优先
 * @create: 2018-11-24 21:54
 **/
@Service
public class SPFService implements ProcessSchedule {

    private static final String TAG = SPFService.class.getSimpleName();

    @Autowired
    private SPFController spfController;

    @Override
    public ResponseData execute(Model... processList) throws InterruptedException {
        if (processList == null || processList.length == 0) {
            throw new NullPointerException("进程为空");
        }

        if (!(processList instanceof SPFModel[])) {
            throw new IllegalArgumentException("数据类型出错");
        }

        ResponseData responseData = new ResponseData();
        int TTimeSum = 0;
        int TWTimeSum = 0;

        SPFModel[] processArray = (SPFModel[])processList;
        boolean[] runFlag = new boolean[processArray.length];
        int runTimeSum = 0;
        int index = 0;
        SPFModel currentProcess = processArray[index];
        while(!ProcessBLL.noProcessWaitting(runFlag)) {
            currentProcess.setStartRunTime(runTimeSum);
            if (runTimeSum < currentProcess.getComingTime()) {
                runTimeSum = (int)currentProcess.getComingTime();
            }

            runTimeSum += currentProcess.getTimeToNeed();
            currentProcess.setFinishTime(runTimeSum);
            currentProcess.setTurnaroundTime(runTimeSum - currentProcess.getComingTime());
            currentProcess.setTurnaroundWeightTime(1.0 * currentProcess.getTurnaroundTime() / currentProcess.getTimeToNeed());

            runFlag[index] = true;

            TTimeSum += currentProcess.getTurnaroundTime();
            TWTimeSum += currentProcess.getTurnaroundWeightTime();

            Thread.sleep(2000);


            spfController.sendSPF(currentProcess);
            index = getIndexMinRuntime(processArray, runFlag, runTimeSum);
            if (0 <= index && index < processArray.length) {
                currentProcess = processArray[index];
            } else {
                throw new UnknownException("未知异常");
            }
        }

        responseData.setTimeSum(runTimeSum);
        responseData.setAveTurnaroundTime(TTimeSum / processArray.length);
        responseData.setAveTurnaroundWeightTime(TWTimeSum / processArray.length);

        return responseData;
    }

    /**
     * 获得进程列表中服务时间最短的进程
     * @param processArray
     *          进程列表
     * @param runFlag
     *          进程运行标志位
     * @return
     *          进程下标
     */
    private int getIndexMinRuntime(SPFModel[] processArray, boolean[] runFlag, int runTimeSum) {
        if (processArray.length == 0 || runFlag.length != processArray.length) {
            return -1;
        }

        int earliestIndex = 0; // 未执行的最早的进程
        long earliestTime = Long.MAX_VALUE;
        int index = -1;
        long minTime = Long.MAX_VALUE;
        for (int i = 0; i < processArray.length; i++) {
            if (runFlag[i]) {
                continue;
            }

            if (processArray[i].getComingTime() < earliestTime) {
                earliestIndex = i;
                earliestTime = processArray[i].getComingTime();
            }

            if (processArray[i].getComingTime() > runTimeSum) {
                continue;
            }

            if (processArray[i].getTimeToNeed() < minTime) {
                minTime = processArray[i].getTimeToNeed();
                index = i;
            }
        }

        index = index < 0 ? earliestIndex : index;

        return index;
    }
}
