package com.baizhi.springb1.service;

import com.baizhi.springb1.entity.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> findAllFu();

    public List<Menu> findAllZi(Integer id);
}
