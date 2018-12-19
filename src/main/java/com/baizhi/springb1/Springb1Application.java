package com.baizhi.springb1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.baizhi.springb1.dao")
@SpringBootApplication
public class Springb1Application {

    public static void main(String[] args) {
        SpringApplication.run(Springb1Application.class, args);
    }

}

