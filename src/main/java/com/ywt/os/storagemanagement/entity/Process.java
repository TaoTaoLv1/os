package com.ywt.os.storagemanagement.entity;

/**
 * @author: YwT
 * @description: 线程类
 * @create: 2018-11-28 16:58
 **/
public class Process {

    private int pId;
    private int pSize;
    private boolean isDealing; //是否分配

    public Process() {
    }

    public Process(int pId, int pSize, boolean isDealing) {
        this.pId = pId;
        this.pSize = pSize;
        this.isDealing = isDealing;
    }

    public boolean isDealing() {
        return isDealing;
    }

    public void setDealing(boolean dealing) {
        isDealing = dealing;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getpSize() {
        return pSize;
    }

    public void setpSize(int pSize) {
        this.pSize = pSize;
    }
}
