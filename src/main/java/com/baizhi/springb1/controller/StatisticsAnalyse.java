package com.baizhi.springb1.controller;

import com.baizhi.springb1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistics")
@Slf4j
public class StatisticsAnalyse {

    @Autowired
    private UserService userService;

    /*public Map<String, List<User>> findUserByGroup(){

    }*/

}
