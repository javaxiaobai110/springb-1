package com.baizhi.springb1.controller;

import com.baizhi.springb1.conf.CreateValidateCode;
import com.baizhi.springb1.entity.Admin;
import com.baizhi.springb1.excp.AdminException;
import com.baizhi.springb1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    @ResponseBody
    public Admin login(String name, String password, String code, HttpSession session) {
        Object cod = session.getAttribute("code");
        if (!cod.equals(code)) {
            throw new AdminException("验证码错误");
        }
        Admin one = adminService.findOne(name, password);
        System.out.println(one + "------------");
        return one;
    }

    @RequestMapping("image")
    public void image(HttpServletResponse response, HttpSession session) throws IOException {
        // 画一张随机图片 --- 使用工具类的write方法画图片
        CreateValidateCode cvc = new CreateValidateCode();
        //获取图片上的随机数  --- 存档  --- session
        String code = cvc.getCode();
        session.setAttribute("code", code);
        //在输出图片
        cvc.write(response.getOutputStream());
        System.out.println("hehe");
    }


}
