package com.baizhi.springb1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class User implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String phone;
    private String password;
    private String salt;
    private String dharma;
    private String sign;
    private String sex;
    private String name;
    @Column(name = "head_pic")
    private String headPic;
    private String province;
    private String city;
    private Integer status;
    @Column(name = "reg_time")
    private Date regTime;
}
