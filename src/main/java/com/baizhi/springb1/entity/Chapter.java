package com.baizhi.springb1.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
@Table(name = "chapter")
public class Chapter implements Serializable {


    @Id
    @KeySql(useGeneratedKeys = true)
    private String id;
    private String title;
    private String size;
    private String duration;
    private String url;
    @Column(name = "upload_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(name = "yyyy-MM-dd")
    private Date uploadDate;
    @Column(name = "alb_id")
    private Integer albId;




}
