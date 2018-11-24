package com.ywt.os.bll;

/**
 * @author: YwT
 * @description: 算法帮助类
 * @create: 2018-11-24 21:57
 **/
public class ProcessBLL {

    /**
     * 判断所有进行是否都已经运行完毕
     * @param runFlag 进程队列中的进程运行情况
     * @return 是否全部执行结束
     */
    public static boolean noProcessWaitting(boolean[] runFlag) {
        for (boolean flag : runFlag) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}
