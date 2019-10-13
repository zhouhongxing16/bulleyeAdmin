package com.chris.bulleyeadmin.common.service;

import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.HttpContextUtils;
import com.chris.bulleyeadmin.common.utils.IPUtils;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.Logger;
import com.chris.bulleyeadmin.system.pojo.Logs;
import com.chris.bulleyeadmin.system.service.LogsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

//记录用户操作日志
@Aspect
@Component
public class LoggerAopService {

    @Autowired
    LogsService logsService;

    private ObjectMapper jsonMapper = new ObjectMapper();

  /*  @Pointcut("execution(* com.chris.bulleyeadmin.*.controller.*.*(..))")
    public void show() {
    }

    @Before("show()")
    public void Before(JoinPoint jp) {
        try {
            Class clz = jp.getTarget().getClass();
            String optionName = "";
            if (clz.isAnnotationPresent( OperationLog.class )) {
                OperationLog opera = (OperationLog) clz.getAnnotation( OperationLog.class );
                optionName = opera.value();
            }
            if (StringUtils.isEmpty( optionName )) {
                return;
            }
            Method[] flds = jp.getTarget().getClass().getMethods();
            String optionType = "";
            for (int i = 0; i < flds.length; i++) {
                if (flds[i].isAnnotationPresent( OperationLog.class ) && flds[i].getName().equals( jp.getSignature().getName() )) {
                    OperationLog opera = flds[i].getAnnotation( OperationLog.class );
                    optionType = opera.value();
                }
            }
            if (StringUtils.isEmpty( optionType )) {
                return;
            }
            Object data = jp.getArgs();
            String ss = "";
            try {
                ss = jsonMapper.writeValueAsString( data ).trim();
            }catch (Exception e){
                e.printStackTrace();
            }
            Logs logs = new Logs();
            logs.setStatus(1);
            logs.setOptionType( optionType );
            logs.setOptionName( optionName );

            if(AuthUtil.getCurrentUser() != null){
                logs.setOrganizationId( AuthUtil.getCurrentUser().getOrganizationId() );
                logs.setUserId( AuthUtil.getCurrentUser().getId() );
            }
            if(!StringUtils.isEmpty( ss )) {
                String content = ss.substring( 1, ss.length() - 1 );
                logs.setParams( Logger.formatJson( content ) );
            }
            logsService.add( logs );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @After("show())")
    public void After(JoinPoint jp) {
//        AuthUtil.local.remove();
    }*/

    @Pointcut("execution(* com.chris.bulleyeadmin.*.controller.*.*(..))")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point, time);
        return result;
    }

    void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class clz = joinPoint.getTarget().getClass();
        String optionName = "";
        if (clz.isAnnotationPresent( OperationLog.class )) {
            OperationLog opera = (OperationLog) clz.getAnnotation( OperationLog.class );
            optionName = opera.value();
        }
        if (StringUtils.isEmpty( optionName )) {
            return;
        }
        Method[] flds = joinPoint.getTarget().getClass().getMethods();
        String optionType = "";
        for (int i = 0; i < flds.length; i++) {
            if (flds[i].isAnnotationPresent( OperationLog.class ) && flds[i].getName().equals( joinPoint.getSignature().getName() )) {
                OperationLog opera = flds[i].getAnnotation( OperationLog.class );
                optionType = opera.value();
            }
        }
        Logs logs = new Logs();
        logs.setStatus(1);
        logs.setOptionType( optionType );
        logs.setOptionName( optionName );
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        logs.setMethod(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        String params = null;
        try {
             params = jsonMapper.writeValueAsString( args ).trim();
            logs.setParams(params);
        } catch (Exception e) {

        }

        if(AuthUtil.getCurrentUser() != null){
            logs.setOrganizationId( AuthUtil.getCurrentUser().getOrganizationId() );
            logs.setUserId( AuthUtil.getCurrentUser().getId() );
        }
        if(!StringUtils.isEmpty( params )) {
            String content = params.substring( 1, params.length() - 1 );
            logs.setParams( Logger.formatJson( content ) );
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        logs.setIp(IPUtils.getIpAddr(request));
        // 系统当前时间
        Date date = new Date();
        logs.setCreated(date);
        logs.setExecutionTime((int)time);
        // 保存系统日志
        logsService.add( logs );
    }



}
