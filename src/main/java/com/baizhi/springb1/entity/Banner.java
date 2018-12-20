package com.baizhi.springb1.entity;

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
@Table(name = "banner")
@Entity
@JsonFormat(pattern = "yyyy-MM-dd")
public class Banner implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    @Column(name = "img_path")
    private String imgPath;
    private Integer status;
    @Column(name = "pub_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pubDate;
    private String description;

}
