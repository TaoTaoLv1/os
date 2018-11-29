package com.ywt.os.storagemanagement.service;

import com.ywt.os.storagemanagement.entity.Process;
import com.ywt.os.storagemanagement.entity.RAM;
import com.ywt.os.storagemanagement.param.RAMAndProcess;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author: YwT
 * @create: 2018-11-28 20:02
 **/
@Service
public class RAMProcessService {

    private static final int RAM_MAX = 100;
    private static final int PROCESS_RAM_MAX = 100;

    public RAMAndProcess init(){
        Random random = new Random();
        int memorySize = random.nextInt(RAM_MAX) + 1;
        int add = 0;

        List<Process> processes; processes = new ArrayList<>();
        List<RAM> rams = new LinkedList<>();

        rams.add(new RAM(add, memorySize, memorySize, true));
        processes.add(new Process(0, random.nextInt(PROCESS_RAM_MAX) + 1, false));

        for (int i = 1; i < 10; i++){
            processes.add(new Process(i, random.nextInt(PROCESS_RAM_MAX) + 1, false));
            memorySize = random.nextInt(RAM_MAX) + 1;
            add += memorySize;
            rams.add(new RAM(add, memorySize, memorySize, false));
        }

        RAMAndProcess ramAndProcess = new RAMAndProcess();
        ramAndProcess.setRams(rams);
        ramAndProcess.setProcesses(processes);

        return ramAndProcess;
    }

    public RAMAndProcess recoverPartition(RAMAndProcess ramAndProcess) {
        List<RAM> rams = ramAndProcess.getRams();
        List<Process> processes = ramAndProcess.getProcesses();

        for (RAM ram : rams) {
            ram.getProcessId().clear();
            ram.setFreeSize(ram.getSize());
        }

        for (Process process : processes) {
            process.setDealing(false);
        }

        return ramAndProcess;
    }
}
