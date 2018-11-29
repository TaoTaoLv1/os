package com.ywt.os.storagemanagement.service;

import com.ywt.os.storagemanagement.entity.Process;
import com.ywt.os.storagemanagement.entity.RAM;
import com.ywt.os.storagemanagement.param.RAMAndProcess;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: YwT
 * @descprition: 最佳适应算法 BF
 * @create: 2018-11-29 14:09
 **/
@Service
public class BFService extends DynamicStoreAlgorithmService {

    @Override
    public RAMAndProcess allocatePartition(RAMAndProcess ramAndProcess) {
        List<RAM> rams = ramAndProcess.getRams();
        List<Process> processes = ramAndProcess.getProcesses();

        for (Process process : processes) {
            rams.sort((a, b) -> (a.getFreeSize() > b.getFreeSize() ? 1 : -1));
            super.judgeSize(rams, process);
        }
        return ramAndProcess;
    }
}
