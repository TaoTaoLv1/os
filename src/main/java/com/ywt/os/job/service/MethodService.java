package com.ywt.os.job.service;

import com.ywt.os.job.entity.Job;
import com.ywt.os.job.param.JobResponse;

import java.util.List;

public interface MethodService {

    JobResponse method(List<Job> Jobs, JobResponse jobResponses);
}
