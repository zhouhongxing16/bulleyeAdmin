package com.chris.bulleyeadmin.config;

import com.chris.bulleyeadmin.pojo.Logger;
import com.chris.bulleyeadmin.pojo.Logs;
import com.chris.bulleyeadmin.service.LogsService;
import com.chris.bulleyeadmin.utils.AuthUtil;
import com.chris.bulleyeadmin.utils.Operalog;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//记录用户操作日志
@Aspect
@Component
public class LoggerAop {

    @Autowired
    LogsService logsService;

    private ObjectMapper jsonMapper = new ObjectMapper();

    @Pointcut("execution(* com.chris.bulleyeadmin.controller.*.*(..))")
    public void show() {
    }


    @Before("show()")
    public void Before(JoinPoint jp) {
        try {
            Class clz = jp.getTarget().getClass();
            String optname = "";
            if (clz.isAnnotationPresent( Operalog.class )) {
                Operalog opera = (Operalog) clz.getAnnotation( Operalog.class );
                optname = opera.value();
            }
            if (StringUtils.isEmpty( optname )) {
                return;
            }
            Method[] flds = jp.getTarget().getClass().getMethods();
            String opttype = "";
            for (int i = 0; i < flds.length; i++) {
                if (flds[i].isAnnotationPresent( Operalog.class ) && flds[i].getName().equals( jp.getSignature().getName() )) {
                    Operalog opera = flds[i].getAnnotation( Operalog.class );
                    opttype = opera.value();
                }
            }
            if (StringUtils.isEmpty( opttype )) {
                return;
            }
            Object data = jp.getArgs();
            String ss = "";
            try {
                ss = jsonMapper.writeValueAsString( data ).trim();
            }catch (Exception e){}
            Logs logs = new Logs();
            if(AuthUtil.getCurrentUser() != null){
                logs.setOrgId( AuthUtil.getCurrentUser().getOrgId() );
                logs.setStaffId( AuthUtil.getCurrentUser().getStaffId() );
                logs.setUserId( AuthUtil.getCurrentUser().getId() );
            }
            logs.setOpttype( opttype );
            logs.setOptname( optname );
            if(!StringUtils.isEmpty( ss )) {
                String content = ss.substring( 1, ss.length() - 1 );
                logs.setContent( Logger.formatJson( content ) );
            }
            logsService.add( logs );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @After("show())")
    public void After(JoinPoint jp) {
//        AuthUtil.local.remove();
    }
}
