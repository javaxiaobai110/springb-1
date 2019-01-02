package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.conf.FileUtil;
import com.baizhi.springb1.dao.UserMapper;
import com.baizhi.springb1.entity.Province;
import com.baizhi.springb1.entity.User;
import com.baizhi.springb1.entity.UserExample;
import com.baizhi.springb1.excp.LoginFailException;
import com.baizhi.springb1.excp.ModifyFailException;
import com.baizhi.springb1.excp.RegisterFailException;
import com.baizhi.springb1.excp.UserImportFailException;
import com.baizhi.springb1.service.UserService;
import com.baizhi.springb1.util.Md5Util;
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

    @Override
    public User findUserOne(String phone, String password) {
        if (phone == null || password == null){
            throw new LoginFailException("系统错误");
        }else{
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhoneEqualTo(phone);
            User user = userMapper.selectOneByExample(userExample);
            if (user == null){
                throw new LoginFailException("账户不存在");
            }else{
                boolean flag = Md5Util.checkPassword(password+user.getSalt(), user.getPassword());
                if (flag) {
                    return user;
                } else{
                    throw new LoginFailException("账号或密码错误");
                }
            }
        }
    }

    @Override
    public User addUser(String phone, String password) {
        if (phone == null || password ==  null){
            throw new RegisterFailException("系统错误");
        }else{

            if (phone.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
                throw new RegisterFailException("手机或密码不能为空");
            }else {
                UserExample userExample = new UserExample();
                userExample.createCriteria().andPhoneEqualTo(phone);
                User user1 = userMapper.selectOneByExample(userExample);
                if (user1 != null){
                    throw new RegisterFailException("该手机号已被注册");
                } else {
                    User user = new User();
                    user.setPhone(phone);
                    String salt = Md5Util.getSalt();
                    user.setSalt(salt);
                    String encryption = Md5Util.encryption(password + user.getSalt());
                    user.setPassword(encryption);
                    userMapper.insertSelective(user);
                    return user;
                }
            }
        }
    }

    @Override
    public User modifyUser(User user) {
        if (user.getId() == null){
            throw new ModifyFailException("系统错误");
        } else {
            if (user.getId().equals("")){
                throw new ModifyFailException("用户id不能为空");
            }else{
                User user1 = userMapper.selectByPrimaryKey(user.getId());
                if (user1 == null) {
                    throw new ModifyFailException("所修改用户不存在");
                } else {
                    String salt = Md5Util.getSalt();
                    String password = user.getPassword();
                    String encryption = Md5Util.encryption(password + salt);
                    user.setPassword(encryption);
                    userMapper.updateByPrimaryKey(user);
                    return user;
                }

            }
        }
    }
}
