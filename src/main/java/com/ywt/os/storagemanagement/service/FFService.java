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
public class FFService implements DynamicStoreAlgorithmService {

    @Override
    public RAMAndProcess allocatePartition(RAMAndProcess ramAndProcess) {
        List<Process> processes = ramAndProcess.getProcesses();
        List<RAM> rams = ramAndProcess.getRams();
        for (Process process : processes) {
            for (RAM ram : rams) {
                if (ram.getFreeSize() >= process.getpSize()){
                    ram.getProcessId().add(process.getpId());
                    ram.setFreeSize(ram.getFreeSize() - process.getpSize());
                    process.setDealing(true);
                    break;
                }
            }
        }

        ramAndProcess.setRams(rams);
        ramAndProcess.setProcesses(processes);
        return ramAndProcess;
    }

}
