package com.baizhi.springb1.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
@ExcelTarget("albumEntity")
public class Album implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    @ExcelIgnore
    private Integer id;
    @Excel(name = "专辑", needMerge = true)
    private String title;
    @Excel(name = "集数",needMerge = true)
    private Integer count;
    @Excel(name = "封面",needMerge = true,type = 2, width = 40, height = 20)
    @Column(name = "cover_img")
    private String coverImg;
    @Excel(name = "作者", needMerge = true)
    private String author;
    @Excel(name = "播音", needMerge = true)
    private String broadcast;
    @Excel(name = "评分", needMerge = true)
    private Integer score;
    @Excel(name = "简介", needMerge = true)
    private String brief;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "pub_date")
    @Excel(name = "发布日期", needMerge = true,format = "YYYY年MM月dd日", width = 20)
    private Date pubDate;
    @Transient
    @ExcelCollection(name = "音频")
    private List<Chapter> children;

}
