package com.baizhi.springb1.service;

import com.baizhi.springb1.entity.Province;
import com.baizhi.springb1.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {


    public List<Province> findUserByGroup();

    public Integer findByRegDate(Integer dateNum);

    public List<User> findByPage();

    public void update(User user);

    public List<User> exportAll();

    public void importUsers(MultipartFile file);

}
