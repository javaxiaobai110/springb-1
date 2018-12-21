package com.baizhi.springb1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.baizhi.springb1.dao")
@SpringBootApplication
@EnableTransactionManagement
public class Springb1Application {

    public static void main(String[] args) {
        SpringApplication.run(Springb1Application.class, args);
    }

}

