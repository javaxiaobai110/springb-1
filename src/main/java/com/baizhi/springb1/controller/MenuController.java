package com.baizhi.springb1.controller;

import com.baizhi.springb1.entity.Admin;
import com.baizhi.springb1.entity.Menu;
import com.baizhi.springb1.entity.U_R;
import com.baizhi.springb1.service.AdminService;
import com.baizhi.springb1.service.MenuService;
import com.baizhi.springb1.service.U_RService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private U_RService u_rService;

    @RequestMapping("showAllFu")
    public List<Menu> showAllFu(){
        List<Menu> allFu = menuService.findAllFu();
        Subject subject = SecurityUtils.getSubject();
        String name = (String) subject.getPrincipal();
        Admin admin = adminService.findOneByName(name);
        U_R u_r = new U_R();
        u_r.setUId(admin.getId());
        List<String> list = new ArrayList<>();
        List<U_R> u_rs = u_rService.queryByU_R(u_r);
        for (U_R uR : u_rs) {
            list.add(String.valueOf(uR.getRId()));
        }
        if (subject.isAuthenticated()){
            boolean aSuper = subject.hasAllRoles(list);
            if (aSuper){
                if (list.contains("1")){
                    return allFu;
                } else {
                    Menu m = null;
                    for (Menu menu : allFu) {
                        if (menu.getText().equals("管理员管理")){
                            m = menu;
                        }
                    }
                    allFu.remove(m);
                    return allFu;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    @RequestMapping(value = "/showAllZi/{id}")
    public List<Menu> showAllZi(@PathVariable("id") Integer id){
        List<Menu> allZi = menuService.findAllZi(id);
        return allZi;

    }

}
