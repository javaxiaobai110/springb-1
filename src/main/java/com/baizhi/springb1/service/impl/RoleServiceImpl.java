package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.dao.RoleMapper;
import com.baizhi.springb1.entity.Role;
import com.baizhi.springb1.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role queryOne() {
        return null;
    }


}
