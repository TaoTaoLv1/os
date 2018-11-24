package com.ywt.os.bll;

import com.ywt.os.process.entity.FCFSModel;
import com.ywt.os.process.entity.Model;
import com.ywt.os.process.entity.RFPFModel;
import com.ywt.os.process.entity.SPFModel;

/**
 * @author: YwT
 * @description: 算法帮助类
 * @create: 2018-11-24 21:57
 **/
public class ProcessBLL {

    /**
     * 判断所有进行是否都已经运行完毕
     * @param runFlag 进程队列中的进程运行情况
     * @return 是否全部执行结束
     */
    public static boolean noProcessWaitting(boolean[] runFlag) {
        for (boolean flag : runFlag) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个进程是否已经到达
     * @param process 进程
     * @param currentTime 当前时间
     * @return 是否到达
     */
    public static boolean isProcessComing(Model process, long currentTime) {


        if (process instanceof FCFSModel) {
            return ((FCFSModel)process).getComingTime() <= currentTime;
        }

        if (process instanceof SPFModel) {
            return ((SPFModel)process).getComingTime() <= currentTime;
        }

        if (process instanceof RFPFModel) {
            return ((RFPFModel)process).getComingTime() <= currentTime;
        }
        return false;
    }
}
