package com.baizhi.springb1;

import com.baizhi.springb1.dao.UserMapper;
import com.baizhi.springb1.entity.Province;
import com.baizhi.springb1.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import io.goeasy.GoEasy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Springb1ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

   /* @Autowired
    private GenerateStorageClient generateStorageClient;*/

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Test
    public void contextLoads() {
        List<Province> provinces = userMapper.queryByGroup();
        provinces.forEach(s-> System.out.println(s));
    }

    @Test
    public void test2(){
        //PageInfo<User> pageInfo = PageHelper.startPage(1,10).setOrderBy("id desc").doSelectPageInfo(()->userMapper.selectAll());
        PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> userService.findByPage());
        List<Object> list = pageInfo.getList();
        list.forEach(s-> System.out.println(s));
        log.info("hehe"+"------------------------"+pageInfo.toString());
    }

    @Test
    public void test() throws FileNotFoundException {
        //FileInfo group1 = fastFileStorageClient.queryFileInfo("group1", "M00/00/00/wKg0i1wjotiAGMTmAAIwwPFU2Bk266.png");
        //group1.getCrc32();
        File file = new File("F:/捕获.png");
        StorePath storePath = fastFileStorageClient.uploadFile(new FileInputStream(file), file.length(), FilenameUtils.getExtension(file.getName()), null);
        System.out.println(storePath);
        //fastFileStorageClient.downloadFile("","",new);
    }

    @Test
    public void test3(){
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-3a52d5e52fdb42908a3f0c20915ca309");
        goEasy.publish("FirstgoEasy", "呵呵HelloWorld, GOEASY");

    }

}

