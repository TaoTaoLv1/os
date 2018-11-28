package com.ywt.os.storagemanagement.service;

import com.ywt.os.storagemanagement.param.RAMAndProcess;

/**
 * @author: YwT
 * @description: 算法接口
 * @create: 2018-11-28 17:08
 **/
public interface DynamicStoreAlgorithmService {

    RAMAndProcess allocatePartition(RAMAndProcess ramAndProcess);  //分配分区
}
