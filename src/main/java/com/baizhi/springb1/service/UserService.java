package com.baizhi.springb1.service;

import com.baizhi.springb1.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {


    public Map<String, List<User>> findUserByGroup();

}
