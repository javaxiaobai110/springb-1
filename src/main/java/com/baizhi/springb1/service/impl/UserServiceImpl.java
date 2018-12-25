package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.conf.FileUtil;
import com.baizhi.springb1.dao.UserMapper;
import com.baizhi.springb1.entity.Province;
import com.baizhi.springb1.entity.User;
import com.baizhi.springb1.excp.UserImportFailException;
import com.baizhi.springb1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override

    public List<Province> findUserByGroup() {
        List<Province> provinces = userMapper.queryByGroup();
        return provinces;
    }

    @Override
    public Integer findByRegDate(Integer dateNum) {
        Integer i = userMapper.findByRegDate(dateNum);
        return i;
    }

    @Override
    public List<User> findByPage() {

        List<User> users = userMapper.selectAll();
        log.info(users.size()+"=================");
        return users;
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> exportAll() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public void importUsers(MultipartFile file) {
        try {
            List<User> users = FileUtil.importExcel(file, 1, 1, User.class);
            for (User user : users) {
                String headPic = user.getHeadPic();
                int i = headPic.lastIndexOf("\\");
                String ss = headPic.substring(i + 1, headPic.length());
                user.setHeadPic(ss);
            }
            userMapper.insertBatch(users);
        } catch (Exception e) {
            throw new UserImportFailException("导入失败");
        }
    }


}
