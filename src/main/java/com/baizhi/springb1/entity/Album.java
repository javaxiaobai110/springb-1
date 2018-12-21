package com.baizhi.springb1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Album implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    private Integer count;
    @Column(name = "cover_img")
    private String coverImg;
    private String author;
    private String broadcast;
    private Integer score;
    private String brief;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "pub_date")
    private Date pubDate;
    @Transient
    private List<Chapter> children;

}
