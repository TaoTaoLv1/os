package com.ywt.os.process.entity;

/**
 * @author: YwT
 * @description: 单个进程
 * @create: 2018-11-24 14:32
 **/
public class Model {

    private String processId; // 进程标识

    private long timeToNeed; // 需要运行时间



    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public long getTimeToNeed() {
        return timeToNeed;
    }

    public void setTimeToNeed(long timeToNeed) {
        this.timeToNeed = timeToNeed;
    }
}
