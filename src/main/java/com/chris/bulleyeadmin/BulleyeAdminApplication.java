package com.chris.bulleyeadmin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.chris.bulleyeadmin.**.mapper")
public class BulleyeAdminApplication {


    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(BulleyeAdminApplication.class);
        ApplicationContext ctx = SpringApplication.run(BulleyeAdminApplication.class, args);
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        logger.info("当前使用的 profile 是:{}", StringUtils.join("、",activeProfiles));
    }
}
