package com.ywt.os.process.service;

import com.ywt.os.process.entity.FCFSModel;
import com.ywt.os.process.entity.Model;
import com.ywt.os.process.param.ResponseData;
import com.ywt.os.process.web.FCFSController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: YwT
 * @description: 先来先服务算法
 * @create: 2018-11-24 14:40
 **/
@Service
public class FCFSService implements ProcessSchedule {

    private static final String TAG = FCFSService.class.getSimpleName();

    @Autowired
    private FCFSController fcfsController;

    @Override
    public ResponseData execute(Model... processList) throws InterruptedException {
        if (null == processList || processList.length == 0) {
            throw new NullPointerException("进程为空");
        }

        if (!(processList instanceof FCFSModel[])) {
            throw new IllegalArgumentException("数据类型出错");
        }

        ResponseData responseData = new ResponseData();
        int TTimeSum = 0;
        int TWTimeSum = 0;

        FCFSModel[] fcfsModels = (FCFSModel[])processList;
        int runTimeSum = 0;
        for (FCFSModel model : fcfsModels) {

            Thread.sleep(2000);

            if (runTimeSum < model.getComingTime()) {
                runTimeSum = (int)model.getComingTime();
            }

            model.setStartRunTime(runTimeSum);
            runTimeSum += model.getTimeToNeed();
            model.setFinishTime(runTimeSum);
            model.setTurnaroundTime(runTimeSum - model.getComingTime());
            model.setTurnaroundWeightTime(1.0 * model.getTurnaroundTime() / model.getTimeToNeed());

            TTimeSum += model.getTurnaroundTime();
            TWTimeSum += model.getTurnaroundWeightTime();

            fcfsController.sendFCFS(model);
        }

        responseData.setTimeSum(runTimeSum);
        responseData.setAveTurnaroundTime(TTimeSum / fcfsModels.length);
        responseData.setAveTurnaroundWeightTime(TWTimeSum / fcfsModels.length);

        return responseData;
    }
}
