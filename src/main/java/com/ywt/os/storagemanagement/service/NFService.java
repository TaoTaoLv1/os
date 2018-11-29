package com.ywt.os.storagemanagement.service;

import com.ywt.os.storagemanagement.entity.Process;
import com.ywt.os.storagemanagement.entity.RAM;
import com.ywt.os.storagemanagement.param.RAMAndProcess;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: YwT
 *@description：循环首次适应算法 NF
 * @create: 2018-11-28 22:33
 **/
@Service
public class NFService extends DynamicStoreAlgorithmService {


    @Override
    public RAMAndProcess allocatePartition(RAMAndProcess ramAndProcess) {
        List<RAM> rams = ramAndProcess.getRams();
        List<Process> processes = ramAndProcess.getProcesses();

        int startPoint = -1;

        //找出标记
        for (RAM ram : rams) {
            if (ram.isStartPoint()){
                startPoint = rams.indexOf(ram) + 1;
                ram.setStartPoint(false);
                break;
            }
        }

        for (Process process : processes) {
            int start = judgeSize(rams, process, startPoint, rams.size());
            if (start != -1){
                //替换 下次 开始查找内存位置
                startPoint = start + 1;
                continue;
            }else {
                start = judgeSize(rams, process, 0, startPoint);
                if (start != -1){
                    startPoint = start + 1;
                    continue;
                }
            }
        }
        rams.get(startPoint - 1).setStartPoint(true);
        return ramAndProcess;
    }

    public int judgeSize(List<RAM> rams, Process process,int start, int end){
        //寻找合适的内存
        for (; start < end; start++){
            if (rams.get(start).getFreeSize() >= process.getpSize()){
                rams.get(start).getProcessId().add(process.getpId());
                rams.get(start).setFreeSize(rams.get(start).getFreeSize() - process.getpSize());
                process.setDealing(true);
                return start;
            }
        }
        return -1;
    }
}
