package com.chris.bulleyeadmin.common.controller;

import com.chris.bulleyeadmin.common.service.QuartzService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.dto.QuartzJobDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by charlie on 2019/5/17.
 */
@Api(tags = "quartz",produces = "任务调度")
@OperationLog("任务调度")
@RestController
@RequestMapping(value="/quartz")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @ApiOperation(value = "添加任务", notes = "添加任务")
    @ApiImplicitParam(name = "添加任务")
    @OperationLog("添加任务")
    @ResponseBody
    @PostMapping("/addJob")
    public void startJob(@RequestBody QuartzJobDto job) {
        try {
            Class<? extends QuartzJobBean> cls = (Class<? extends QuartzJobBean>) Class.forName(job.getJobClass());
            if(StringUtils.isNotEmpty(job.getCronExpression())) {
                quartzService.addJob(cls,job.getJobName(),job.getJobGroup(),job.getCronExpression());
            } else {
                quartzService.addJob(cls, job.getJobName(), job.getJobGroup(), job.getInterval (),job.getJobTimes());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @ApiOperation(value = "更新任务", notes = "更新任务")
    @ApiImplicitParam(name = "更新任务")
    @OperationLog("更新任务")
    @PostMapping("/updateJob")
    public void updateJob(@RequestBody QuartzJobDto job) {
        if(StringUtils.isNotEmpty(job.getCronExpression())) {
            quartzService.updateJob(job.getJobName(), job.getJobGroup(), job.getCronExpression());
        } else {
            quartzService.updateJob(job.getJobName(),job.getJobGroup(),job.getInterval(),job.getJobTimes());
        }
    }

    @ApiOperation(value = "删除任务", notes = "删除任务")
    @ApiImplicitParam(name = "删除任务")
    @OperationLog("删除任务")
    @PostMapping("/deleteJob")
    public void deleteJob(@RequestBody QuartzJobDto job) {
        quartzService.deleteJob(job.getJobName(),job.getJobGroup());
    }

    @ApiOperation(value = "暂停任务", notes = "暂停任务")
    @ApiImplicitParam(name = "暂停任务")
    @OperationLog("暂停任务")
    @PostMapping("/pauseJob")
    public void pauseJob(@RequestBody QuartzJobDto job) {
        quartzService.pauseJob(job.getJobName(),job.getJobGroup());
    }

    @ApiOperation(value = "恢复暂停任务", notes = "恢复暂停任务")
    @ApiImplicitParam(name = "恢复暂停任务")
    @OperationLog("恢复暂停任务")
    @PostMapping("/resumeJob")
    public void resumeJob(@RequestBody QuartzJobDto job) {
        quartzService.resumeJob(job.getJobName(),job.getJobGroup());
    }

    @ApiOperation(value = "查询所有任务", notes = "查询所有任务")
    @ApiImplicitParam(name = "查询所有任务")
    @OperationLog("查询所有任务")
    @PostMapping("/queryAllJob")
    public Object queryAllJob(@RequestBody Map<String, Object> params) {
        return quartzService.queryAllJob(params);
    }

    @ApiOperation(value = "查询运行中任务", notes = "查询运行中任务")
    @ApiImplicitParam(name = "查询运行中任务")
    @OperationLog("查询运行中任务")
    @PostMapping("/queryRunJob")
    public Object queryRunJob() {
        return quartzService.queryRunJob();
    }
}
