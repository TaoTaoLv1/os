package com.ywt.os.process.service;

import com.ywt.os.process.entity.FCFSModel;
import com.ywt.os.process.entity.Model;

/**
 * @author: YwT
 * @description: 先来先服务算法
 * @create: 2018-11-24 14:40
 **/
public class FCFSService implements ProcessSchedule {
    private static final String TAG = FCFSService.class.getSimpleName();

    @Override
    public int execute(Model... processList) {
        if (processList == null || processList.length == 0) {
            System.out.println(TAG + ">数据为空");
            return -1;
        }

        if (!(processList instanceof FCFSModel[])) {
            System.out.println(TAG + ">数据类型出错");
            return -2;
        }

        FCFSModel[] fcfsModels = (FCFSModel[])processList;
        int runTimeSum = 0;
        for (FCFSModel model : fcfsModels) {
            if (runTimeSum < model.getComingTime()) {
                runTimeSum = (int)model.getComingTime();
            }

            model.setStartRunTime(runTimeSum);
            runTimeSum += model.getTimeToNeed();
            model.setFinishTime(runTimeSum);
            model.setTurnaroundTime(runTimeSum - model.getComingTime());
            model.setTurnaroundWeightTime(1.0 * model.getTurnaroundTime() / model.getTimeToNeed());
        }

        return runTimeSum;
    }
}
