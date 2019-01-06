package com.baizhi.springb1.controller;

import com.baizhi.springb1.conf.CreateValidateCode;
import com.baizhi.springb1.excp.AdminException;
import com.baizhi.springb1.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
@Slf4j
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("test1")
    public String test1(Date time){
        return null;
    }

    @RequestMapping("test")
    public String test(){
        return "redirect:/login.jsp";
    }

    @RequestMapping("login")
    @ResponseBody
    public void login(String name, String password, String code, HttpSession session) {
        Object cod = session.getAttribute("code");
        String cd = (String)cod;
        if (!cd.equalsIgnoreCase(code)) {
            throw new AdminException("checkcode fail");
        }
        /*Admin one = adminService.findOne(name, password);
        session.setAttribute("admin",one);
        return one;*/
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(name, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            throw new AdminException("username or password fail");
        }
    }

    @RequestMapping("image")
    public void image(HttpServletResponse response, HttpSession session) throws IOException {
        // 画一张随机图片 --- 使用工具类的write方法画图片
        CreateValidateCode cvc = new CreateValidateCode();
        String s = "hehe";

        //获取图片上的随机数  --- 存档  --- session
        String code = cvc.getCode();
        session.setAttribute("code", code);
        //在输出图片
        cvc.write(response.getOutputStream());
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }

}
