package com.baizhi.springb1.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@ExcelTarget(value = "userEntity")
public class User implements Serializable {



    @Id
    @KeySql(useGeneratedKeys = true)
    @ExcelIgnore
    private Integer id;
    @Excel(name = "用户名")
    private String phone;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "盐")
    private String salt;
    @Excel(name = "上师")
    private String dharma;
    @Excel(name = "签名")
    private String sign;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "头像",type = 2,width = 20,height = 10, savePath = "D:/ideaworkspace/springb/springb-1/src/main/webapp/img")
    @Column(name = "head_pic")
    private String headPic;
    @Excel(name = "省份")
    private String province;
    @Excel(name = "城市")
    private String city;
    @Excel(name = "状态")
    private Integer status;
    @Column(name = "reg_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间",format = "YYYY年MM月dd日",width = 20)
    private Date regTime;
}
