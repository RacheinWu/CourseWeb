package com.he.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.he.ssm.dao"})
@SpringBootApplication
public class Run {


    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

}
