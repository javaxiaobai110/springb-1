package com.baizhi.springb1.controller;

import com.baizhi.springb1.entity.Province;
import com.baizhi.springb1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("findUserByGroup")
    public Map<String,List<Province>> findUserByGroup(){
        List<Province> list = userService.findUserByGroup();
        Map<String,List<Province>> map = new HashMap<>();
        map.put("data",list);
        return map;
    }

    @RequestMapping("/findByRegDate/{week}")
    public Map<String,Object> findByRegDate(@PathVariable("week") Integer week){
        Map<String,Object> map = new HashMap<>();
        List<String> str = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for (int i = 1; i <= week; i++) {
            Integer userNumber = userService.findByRegDate(i * 7);
            str.add("近"+(i*7/7)+"周");
            counts.add(userNumber);
        }
        map.put("intervals",str);
        map.put("counts",counts);
        return map;
    }


}
