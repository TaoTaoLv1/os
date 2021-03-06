package com.ywt.os.storagemanagement.service;

import com.ywt.os.storagemanagement.entity.Process;
import com.ywt.os.storagemanagement.entity.RAM;
import com.ywt.os.storagemanagement.param.RAMAndProcess;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: YwT
 * @description：首次适应算法 FF
 * @create: 2018-11-28 18:02
 **/
@Service
public class FFService extends DynamicStoreAlgorithmService {

    @Override
    public RAMAndProcess allocatePartition(RAMAndProcess ramAndProcess) {
        List<Process> processes = ramAndProcess.getProcesses();
        List<RAM> rams = ramAndProcess.getRams();
        for (Process process : processes) {
            super.judgeSize(rams, process);
        }
        return ramAndProcess;
    }

}
