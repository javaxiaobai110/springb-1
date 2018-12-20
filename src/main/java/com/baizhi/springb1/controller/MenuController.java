package com.baizhi.springb1.controller;

import com.baizhi.springb1.entity.Menu;
import com.baizhi.springb1.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("showAllFu")
    public List<Menu> showAllFu(){
        List<Menu> allFu = menuService.findAllFu();
        return allFu;
    }

    @RequestMapping(value = "/showAllZi/{id}")
    public List<Menu> showAllZi(@PathVariable("id") Integer id){
        List<Menu> allZi = menuService.findAllZi(id);
        return allZi;

    }

}
