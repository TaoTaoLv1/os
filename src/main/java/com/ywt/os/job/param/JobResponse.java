package com.ywt.os.job.param;

import com.ywt.os.job.entity.Job;

import java.util.List;

public class JobResponse {

    private List<Job> job;

    private List list;

    public List<Job> getJob() {
        return job;
    }

    public void setJob(List<Job> job) {
        this.job = job;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
