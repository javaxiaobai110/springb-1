package com.baizhi.springb1;

import com.baizhi.springb1.conf.FileUtil;
import com.baizhi.springb1.entity.Album;

import java.io.IOException;
import java.util.List;

public class AudioTest {
    public static void main(String[] args) throws IOException {

            String filePath = "F:/呵呵.xls";
            //解析excel，
            List<Album> list = FileUtil.importExcel(filePath,1,2,Album.class);
            //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
            list.forEach(s-> System.out.println(s));



    }
}
