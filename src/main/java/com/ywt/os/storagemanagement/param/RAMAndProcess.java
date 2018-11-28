package com.ywt.os.storagemanagement.param;

import com.ywt.os.storagemanagement.entity.Process;
import com.ywt.os.storagemanagement.entity.RAM;

import java.util.List;

/**
 * @author: YwT
 * @description: 返回 内存，线程组
 * @create: 2018-11-28 20:38
 **/
public class RAMAndProcess {

    private List<RAM> rams;
    private List<Process> processes;

    public RAMAndProcess() {
    }

    public List<RAM> getRams() {
        return rams;
    }

    public void setRams(List<RAM> rams) {
        this.rams = rams;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }
}
