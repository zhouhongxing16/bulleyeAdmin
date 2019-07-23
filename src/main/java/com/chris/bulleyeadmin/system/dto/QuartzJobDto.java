package com.chris.bulleyeadmin.system.dto;

import java.io.Serializable;

/**
 * Created by charlie on 2019/5/17.
 */
public class QuartzJobDto implements Serializable {

    private String jobName;

    private String jobClass;

    private String jobGroup;

    private String cronExpression;

    private int interval;

    private int jobTimes;

    private String jobStatus;


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getJobTimes() {
        return jobTimes;
    }

    public void setJobTimes(int jobTimes) {
        this.jobTimes = jobTimes;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}
