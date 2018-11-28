package com.ywt.os.storagemanagement.entity;

/**
 * @author: YwT
 * @descprition: 内存
 * @create: 2018-11-28 17:04
 **/
public class RAM {

    private String processName; //正在使用的进程号
    private boolean free;		//分区状态 1.true表示空闲 2.false表示使用
    private int size;           //分区大小

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
