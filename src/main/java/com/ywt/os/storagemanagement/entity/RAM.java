package com.ywt.os.storagemanagement.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: YwT
 * @descprition: 内存
 * @create: 2018-11-28 17:04
 **/
public class RAM {

    private  List<Integer> processId; //正在使用的进程号
    private int startIndex;		//开始地址
    private int size;           //分区大小
    private int freeSize;      //剩余大小

    public RAM() {
    }

    public RAM(int startIndex, int size, int freeSize) {
        this.startIndex = startIndex;
        this.size = size;
        this.freeSize = freeSize;
        this.setProcessId(new ArrayList<>());
    }

    public int getFreeSize() {
        return freeSize;
    }

    public void setFreeSize(int freeSize) {
        this.freeSize = freeSize;
    }

    public List<Integer> getProcessId() {
        return processId;
    }

    public void setProcessId(List<Integer> processId) {
        this.processId = processId;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
