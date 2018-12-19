package com.baizhi.springb1.service;

import com.baizhi.springb1.entity.Admin;


public interface AdminService {

    public Admin findOne(String username, String password);
}
