package com.chris.bulleyeadmin.common.service;

import com.chris.bulleyeadmin.common.config.SchedulerJobListener;
import com.chris.bulleyeadmin.system.dto.QuartzJobDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.PageObjectUtil;
import org.quartz.*;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by charlie on 2019/5/17.
 */
@Service
public class QuartzService {

    @Autowired
    private Scheduler scheduler;

    private SchedulerJobListener jobListener;

    @PostConstruct
    public void startScheduler() {
        try {
            if(null == jobListener) {
                jobListener = new SchedulerJobListener();
                scheduler.getListenerManager().addJobListener(jobListener);
            }
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个job
     *
     * @param jobClass
     *            任务实现类
     * @param jobName
     *            任务名称
     * @param jobGroupName
     *            任务组名
     * @param interval
     *            时间表达式 (这是每隔多少秒为一次任务)
     * @param jobTimes
     *            运行的次数 （<0:表示不限次数）
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, int interval,
                       int jobTimes) {
        try {
            JobDetail jobDetail = createJob(jobClass,jobName,jobGroupName);
            if(jobDetail != null) {
                // 使用simpleTrigger规则
                Trigger trigger = createSimpleTrigger(jobName, jobGroupName, interval, jobTimes);
                scheduler.scheduleJob(jobDetail, trigger);
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个job
     *
     * @param jobClass
     *            任务实现类
     * @param jobName
     *            任务名称
     * @param jobGroupName
     *            任务组名
     * @param cronExpression
     *            时间表达式 （如：0/5 * * * * ? ）
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, String cronExpression) {
        try {
            // 把作业和触发器注册到任务调度中
            JobDetail jobDetail = createJob(jobClass,jobName,jobGroupName);
            if(jobDetail != null)  {
                Trigger trigger = createCronTrigger(cronExpression,jobName,jobGroupName);
                scheduler.scheduleJob(jobDetail,trigger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JobDetail createJob(Class jobClass,String jobName,String jobGroupName) throws SchedulerException {

        JobKey jobKey = new JobKey(jobName,jobGroupName);
        if(scheduler.checkExists(jobKey)) return null;
        // 创建jobDetail实例，绑定Job实现类
        // 指明job的名称，所在组的名称，以及绑定job类
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey)
                .storeDurably(true)// 任务名称和组构成任务key
                .build();

        return jobDetail;
    }

    private CronTrigger createCronTrigger(String cronExpression,String jobName,String jobGroupName) {
        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                .startAt(DateBuilder.futureDate(1, IntervalUnit.SECOND))
                .withSchedule(scheduleBuilder).build();

        return trigger;
    }

    /**
     * 修改 一个job的 时间表达式
     *
     * @param jobName
     * @param jobGroupName
     * @param cronExpression
     */
    public void updateJob(String jobName, String jobGroupName, String cronExpression) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);

            Trigger trigger = scheduler.getTrigger(triggerKey);
            if(trigger == null) {
                return ;
            }

            trigger = createCronTrigger(cronExpression,jobName,jobGroupName);

            // 重启触发器
            scheduler.rescheduleJob(triggerKey,trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void updateJob(String jobName, String jobGroupName,int interval,int times) {
        try {
            JobDetail jobDetail = scheduler.getJobDetail(new JobKey(jobName,jobGroupName));
            Class objJobClass = jobDetail.getJobClass();
            if(objJobClass != null) {
                removeJob(jobName, jobGroupName);
                addJob(objJobClass,jobName,jobGroupName,interval,times);
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void removeJob(String jobName, String jobGroupName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            deleteJob(jobName,jobGroupName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Trigger createSimpleTrigger(String jobName, String jobGroupName,int interval,int times) {
        Trigger trigger = null;
        if (times <= 0) {
            trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1)
                            .withIntervalInSeconds(interval))
                    .startNow().build();
        } else {
            trigger = TriggerBuilder
                    .newTrigger().withIdentity(jobName, jobGroupName).withSchedule(SimpleScheduleBuilder
                            .repeatSecondlyForever(1).
                                    withIntervalInSeconds(interval).withRepeatCount(times))
                    .startNow().build();
        }
        return trigger;
    }
    /**
     * 删除任务一个job
     *
     * @param jobName
     *            任务名称
     * @param jobGroupName
     *            任务组名
     */
    public void deleteJob(String jobName, String jobGroupName) {
        try {
            scheduler.deleteJob(new JobKey(jobName, jobGroupName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    public void pauseJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    public void resumeJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 立即执行一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    public void runJobNow(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     */
    public PageInfo queryAllJob(Map<String, Object> params) {
        List<QuartzJobDto> jobList = null;
        PageInfo page = null;
        try {
            int start = 0;
            int end = 0;
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Object[] jobKeys = scheduler.getJobKeys(matcher).toArray();
            if (params != null) {
                Page p = PageObjectUtil.getPageFromObject(params,true);
                start = p.getStartRow();
                end = start + p.getPageSize();
                if(end>jobKeys.length) {
                    end = jobKeys.length;
                }
            } else {
                end = jobKeys.length;
            }
            jobList = new ArrayList<QuartzJobDto>();
            for (int i=start;i<end;i++) {
                JobKey jobKey = (JobKey) jobKeys[i];
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                QuartzJobDto dto = new QuartzJobDto();
                dto.setJobName(jobKey.getName());
                dto.setJobGroup(jobKey.getGroup());
                dto.setJobClass(jobKey.getClass().getName());
                dto.setJobStatus("已过期");
                for (Trigger trigger : triggers) {
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    dto.setJobStatus(triggerState.name());
                    if(trigger instanceof SimpleTrigger) {
                        SimpleTrigger simpleTrigger = (SimpleTrigger) trigger;
                        dto.setInterval((int)simpleTrigger.getRepeatInterval());
                        dto.setJobTimes(simpleTrigger.getRepeatCount());
                    }
                    else if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        dto.setCronExpression(cronExpression);
                    }
                    break;
                }

                jobList.add(dto);
            }
            page = new PageInfo(jobList);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return page;
    }

    /**
     * 获取所有正在运行的job
     *
     * @return
     */
    public List<Map<String, Object>> queryRunJob() {
        List<Map<String, Object>> jobList = null;
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            jobList = new ArrayList<Map<String, Object>>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                Map<String, Object> map = new HashMap<String, Object>();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                map.put("jobName", jobKey.getName());
                map.put("jobGroupName", jobKey.getGroup());
                map.put("description", "触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                map.put("jobStatus", triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    map.put("jobTime", cronExpression);
                }
                jobList.add(map);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }

}
