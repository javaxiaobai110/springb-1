package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.dao.UserMapper;
import com.baizhi.springb1.entity.User;
import com.baizhi.springb1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, List<User>> findUserByGroup() {
        return null;
    }
}
