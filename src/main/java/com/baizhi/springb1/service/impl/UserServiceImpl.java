package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.dao.UserMapper;
import com.baizhi.springb1.entity.Province;
import com.baizhi.springb1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override

    public List<Province> findUserByGroup() {
        List<Province> provinces = userMapper.queryByGroup();
        return provinces;
    }

    @Override
    public Integer findByRegDate(Integer dateNum) {
        Integer i = userMapper.findByRegDate(dateNum);
        return i;
    }




}
