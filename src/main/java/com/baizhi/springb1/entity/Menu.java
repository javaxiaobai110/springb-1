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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
@Entity
public class Menu implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @Column(name = "title")
    private String text;
    @Column(name = "iconcls")
    private String iconCls;
    private String url;
    @Column(name = "parent_id")
    private Integer parentId;


}
