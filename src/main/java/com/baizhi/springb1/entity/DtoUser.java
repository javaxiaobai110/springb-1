package com.baizhi.springb1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser implements Serializable {

    private List<User> rows;
    private Integer total;

}
