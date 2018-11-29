package com.ywt.os.storagemanagement.service;

import com.ywt.os.storagemanagement.entity.Process;
import com.ywt.os.storagemanagement.entity.RAM;
import com.ywt.os.storagemanagement.param.RAMAndProcess;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: YwT
 * @description: 最坏适应算法 WF
 * @create: 2018-11-29 14:33
 **/
@Service
public class WFService extends DynamicStoreAlgorithmService {

    @Override
    public RAMAndProcess allocatePartition(RAMAndProcess ramAndProcess) {
        List<RAM> rams = ramAndProcess.getRams();
        List<Process> processes = ramAndProcess.getProcesses();

        for (Process process : processes) {
            rams.sort((a, b) -> (a.getFreeSize() > b.getFreeSize() ? -1 : 1));
            super.judgeSize(rams, process);
        }
        return ramAndProcess;
    }
}
