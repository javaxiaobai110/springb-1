package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.dao.MenuMapper;
import com.baizhi.springb1.entity.Menu;
import com.baizhi.springb1.entity.MenuExample;
import com.baizhi.springb1.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAllFu() {
        MenuExample me = new MenuExample();
        me.createCriteria().andParentIdIsNull();
        List<Menu> menus = menuMapper.selectByExample(me);
        return menus;
    }

    @Override
    public List<Menu> findAllZi(Integer id) {
        MenuExample me = new MenuExample();
        me.createCriteria().andParentIdEqualTo(id);
        List<Menu> menus = menuMapper.selectByExample(me);
        return menus;
    }
}
