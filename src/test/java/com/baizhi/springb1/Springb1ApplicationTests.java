package com.baizhi.springb1;

import com.baizhi.springb1.dao.UserMapper;
import com.baizhi.springb1.entity.Province;
import com.baizhi.springb1.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Springb1ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        List<Province> provinces = userMapper.queryByGroup();
        provinces.forEach(s-> System.out.println(s));
    }

    @Test
    public void test2(){
        //PageInfo<User> pageInfo = PageHelper.startPage(1,10).setOrderBy("id desc").doSelectPageInfo(()->userMapper.selectAll());
        PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> userService.findByPage());
        List<Object> list = pageInfo.getList();
        list.forEach(s-> System.out.println(s));
        log.info("hehe"+"------------------------"+pageInfo.toString());
    }

}

