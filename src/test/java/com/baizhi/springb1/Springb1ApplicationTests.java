package com.baizhi.springb1;

import com.baizhi.springb1.dao.UserMapper;
import com.baizhi.springb1.entity.Province;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springb1ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        List<Province> provinces = userMapper.queryByGroup();
        provinces.forEach(s-> System.out.println(s));
    }

}

