package com.ywt.os.process.param;

import com.ywt.os.process.entity.Model;

/**
 * @author: YwT
 * @description: 封装返回数据
 * @create: 2018-11-25 13:09
 **/
public class ResponseData {

    private long timeSum; //总时间

    private double aveTurnaroundTime; //平均周转时间

    private double aveTurnaroundWeightTime; //平均带权周转时间

    public long getTimeSum() {
        return timeSum;
    }

    public void setTimeSum(long timeSum) {
        this.timeSum = timeSum;
    }

    public double getAveTurnaroundTime() {
        return aveTurnaroundTime;
    }

    public void setAveTurnaroundTime(double aveTurnaroundTime) {
        this.aveTurnaroundTime = aveTurnaroundTime;
    }

    public double getAveTurnaroundWeightTime() {
        return aveTurnaroundWeightTime;
    }

    public void setAveTurnaroundWeightTime(double aveTurnaroundWeightTime) {
        this.aveTurnaroundWeightTime = aveTurnaroundWeightTime;
    }
}
