package com.baizhi.springb1.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
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
@ExcelTarget(value = "chapterEntity")
public class Chapter implements Serializable {


    @Id
    @KeySql(useGeneratedKeys = true)
    @ExcelIgnore
    private String id;
    @Excel(name = "章节名")
    private String title;
    @Excel(name = "大小")
    private String size;
    @Excel(name = "时长")
    private String duration;
    @Excel(name = "url", width = 45)
    private String url;
    @Column(name = "upload_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(name = "yyyy-MM-dd")
    @Excel(name = "上传日期", format = "YYYY年MM月dd日", width = 20)
    private Date uploadDate;
    @Column(name = "alb_id")
    @ExcelIgnore
    private Integer albId;




}
