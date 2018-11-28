package com.ywt.os.storagemanagement.service;

import com.ywt.os.storagemanagement.entity.Process;

/**
 * @author: YwT
 * @description: 算法接口
 * @create: 2018-11-28 17:08
 **/
public interface DynamicStoreAlgorithmService {
    void init(int size);   //初始化分区链表
    boolean allocatePartition(Process process);  //分配分区
    boolean recoverPartition(Process process);   //回收分区
    void unitPartition(Process process);  //合并空闲分区
    void show(); //显示当前的内存状态
}
