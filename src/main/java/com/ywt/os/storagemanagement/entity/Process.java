package com.ywt.os.storagemanagement.entity;

/**
 * @author: YwT
 * @description: 线程类
 * @create: 2018-11-28 16:58
 **/
public class Process {

    private String pName;
    private int pSize;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getpSize() {
        return pSize;
    }

    public void setpSize(int pSize) {
        this.pSize = pSize;
    }
}
