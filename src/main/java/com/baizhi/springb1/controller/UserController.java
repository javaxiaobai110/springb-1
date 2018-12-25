package com.baizhi.springb1.controller;

import com.baizhi.springb1.conf.FileUtil;
import com.baizhi.springb1.entity.DtoUser;
import com.baizhi.springb1.entity.Province;
import com.baizhi.springb1.entity.User;
import com.baizhi.springb1.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @RequestMapping("importUsers")
    public void importUsers(MultipartFile file){
        userService.importUsers(file);
    }

    @RequestMapping("exportAllUser")
    public void exportAllUser(HttpServletResponse response){
        List<User> users = userService.exportAll();
        for (User user : users) {
            String realPath = env.getProperty("file.real.path");
            user.setHeadPic(realPath+File.separatorChar+user.getHeadPic());
        }
        try {
            String encode = URLEncoder.encode("哈哈.xls", "UTF-8");
            response.setHeader("content-disposition","attachment;filename="+encode);
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            FileUtil.exportExcel(users,"所有用户","用户管理",User.class,"哈哈.xls",response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("updateUser")
    public void update(User user){
        userService.update(user);
    }

    @RequestMapping("byPage")
    public DtoUser findUserByPage(int page, int rows){
        PageInfo<User> pageInfo = PageHelper.startPage(page, rows).setOrderBy("id desc").doSelectPageInfo(() -> userService.findByPage());
        //int pageNum = pageInfo.getPageNum();
        //int pageSize = pageInfo.getPageSize();
        long total = pageInfo.getTotal();
        List<User> list = pageInfo.getList();
        DtoUser dtoUser = new DtoUser();
        dtoUser.setRows(list);
        dtoUser.setTotal((int) total);
        return dtoUser;
    }

    @RequestMapping("findUserByGroup")
    public Map<String,List<Province>> findUserByGroup(){
        List<Province> list = userService.findUserByGroup();
        Map<String,List<Province>> map = new HashMap<>();
        map.put("data",list);
        return map;
    }

    @RequestMapping("/findByRegDate/{week}")
    public Map<String,Object> findByRegDate(@PathVariable("week") Integer week){
        Map<String,Object> map = new HashMap<>();
        List<String> str = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for (int i = 1; i <= week; i++) {
            Integer userNumber = userService.findByRegDate(i * 7);
            str.add("近"+(i*7/7)+"周");
            counts.add(userNumber);
        }
        map.put("intervals",str);
        map.put("counts",counts);
        return map;
    }




}
