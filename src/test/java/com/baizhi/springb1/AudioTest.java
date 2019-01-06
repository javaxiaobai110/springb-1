package com.baizhi.springb1;

import com.baizhi.springb1.util.FileUtil;
import com.baizhi.springb1.entity.User;

import java.io.IOException;
import java.util.List;

public class AudioTest {
    public static void main(String[] args) throws IOException {

            String filePath = "F:/哈哈.xls";
            //解析excel，
            List<User> list = FileUtil.importExcel(filePath,1,1,User.class);
            //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
            for (User user : list) {
                String headPic = user.getHeadPic();
                int i = headPic.lastIndexOf("\\");
                String substring = headPic.substring(i+1, headPic.length());
                System.out.println(substring+">>>>>>>>>>>>>>>>");


            }
            list.forEach(s-> System.out.println(s));
    }
}
