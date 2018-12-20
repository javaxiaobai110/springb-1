package com.baizhi.springb1.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DtoBanner implements Serializable {

    private Integer total;
    private List<Banner> rows;


}
