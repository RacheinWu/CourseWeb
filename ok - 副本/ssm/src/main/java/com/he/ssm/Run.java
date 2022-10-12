package com.he.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan(basePackages = {"com.he.ssm.dao"})
@SpringBootApplication
@EnableScheduling //定时器注解
public class Run {


    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

}
