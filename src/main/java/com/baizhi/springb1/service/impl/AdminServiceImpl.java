package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.dao.AdminMapper;
import com.baizhi.springb1.entity.Admin;
import com.baizhi.springb1.excp.AdminException;
import com.baizhi.springb1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findOne(String username, String password) {
        Admin admin = new Admin();
        admin.setName(username);
        admin.setPassword(password);
        Admin admin1 = adminMapper.selectOne(admin);
        if (admin1 == null) {
            throw new AdminException("username or password fail");
        }
        return admin1;
    }
}
