package com.ywt.os.process.service;

import com.ywt.os.process.entity.Model;
import com.ywt.os.process.param.ResponseData;

/**
 * 进程调度接口
 * 进程调度算法类都是要来实现这个接口
 */
public interface ProcessSchedule {
    /**
     * @param processList 进程列表
     * @return int 总时间
     */
    ResponseData execute(Model... processList) throws InterruptedException;
}
