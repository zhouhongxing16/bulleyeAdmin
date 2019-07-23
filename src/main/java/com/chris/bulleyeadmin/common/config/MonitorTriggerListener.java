package com.chris.bulleyeadmin.common.config;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * Created by charlie on 2019/5/23.
 */
public class MonitorTriggerListener implements TriggerListener {
    @Override
    public String getName() {
        return "MonitorTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        try {
            JobDetail jobDetail = context.getJobDetail();
            System.out.println("------------" + jobDetail.getKey().getName()+"开始--------------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        try {
            JobDetail jobDetail = context.getJobDetail();
            System.out.println("------------" + jobDetail.getKey().getName()+"完成--------------");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
