package com.ywt.os.process.entity;

/**
 * @author: YwT
 * @description: 非抢占式优先权算法(相对优先权)进程对象
 * @create: 2018-11-24 22:14
 **/
public class RFPFModel extends Model {
    private long comingTime; // 到达时间
    private int priority; // 进程优先级
    private long startRunTime; // 开始执行时间
    private long finishTime; // 完成时间
    private long turnaroundTime; // 周转时间
    private double turnaroundWeightTime; // 带权周转时间

    public RFPFModel(String processId, long timeToNeed, long comingTime, int priority) {
        setProcessId(processId);
        setTimeToNeed(timeToNeed);
        this.comingTime = comingTime;
        this.priority = priority;
    }

    public long getComingTime() {
        return comingTime;
    }

    public void setComingTime(long comingTime) {
        this.comingTime = comingTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getStartRunTime() {
        return startRunTime;
    }

    public void setStartRunTime(long startRunTime) {
        this.startRunTime = startRunTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public long getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(long turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public double getTurnaroundWeightTime() {
        return turnaroundWeightTime;
    }

    public void setTurnaroundWeightTime(double turnaroundWeightTime) {
        this.turnaroundWeightTime = turnaroundWeightTime;
    }
}
