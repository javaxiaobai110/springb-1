package com.baizhi.springb1.service;

import com.baizhi.springb1.entity.Province;

import java.util.List;

public interface UserService {


    public List<Province> findUserByGroup();

    public Integer findByRegDate(Integer dateNum);

}
