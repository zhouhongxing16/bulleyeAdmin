package com.chris.bulleyeadmin.common.service;

import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.Logger;
import com.chris.bulleyeadmin.system.pojo.Logs;
import com.chris.bulleyeadmin.system.service.LogsService;
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
public class LoggerAopService {

    @Autowired
    LogsService logsService;

    private ObjectMapper jsonMapper = new ObjectMapper();

    @Pointcut("execution(* com.chris.bulleyeadmin.*.controller.*.*(..))")
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
