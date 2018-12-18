package com.chris.bulleyeadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.chris.bulleyeadmin.mapper")
public class BulleyeAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BulleyeAdminApplication.class, args);
    }
}
