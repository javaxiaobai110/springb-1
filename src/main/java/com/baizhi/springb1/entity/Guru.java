package com.baizhi.springb1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guru")
public class Guru implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String dharma;
    private String headPic;
    private Integer status;
}
