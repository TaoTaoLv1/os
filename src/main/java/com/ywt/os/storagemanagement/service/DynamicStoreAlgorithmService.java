package com.ywt.os.storagemanagement.service;

import com.ywt.os.storagemanagement.entity.Process;
import com.ywt.os.storagemanagement.entity.RAM;
import com.ywt.os.storagemanagement.param.RAMAndProcess;

import java.util.List;

/**
 * @author: YwT
 * @description: 算法接口
 * @create: 2018-11-28 17:08
 **/
public abstract class DynamicStoreAlgorithmService {

    public  abstract RAMAndProcess allocatePartition(RAMAndProcess ramAndProcess);  //分配分区

    public void judgeSize(List<RAM> rams, Process process){
        for (RAM ram : rams) {
            if (ram.getFreeSize() >= process.getpSize()){
                ram.getProcessId().add(process.getpId());
                ram.setFreeSize(ram.getFreeSize() - process.getpSize());
                process.setDealing(true);
                break;
            }
        }

    }
}
