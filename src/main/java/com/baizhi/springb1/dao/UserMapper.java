package com.baizhi.springb1.dao;

import com.baizhi.springb1.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    public List<User> queryByGroup();
}
