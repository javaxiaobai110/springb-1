package com.baizhi.springb1.controller;

import com.baizhi.springb1.conf.UploadUtils;
import com.baizhi.springb1.entity.Chapter;
import com.baizhi.springb1.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("chapter")
@Slf4j
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("add")
    public void add(Chapter chapter, @RequestParam("file")MultipartFile file) throws IOException {
        String fileName = UploadUtils.getFileName(file.getOriginalFilename());
        File file1 = new File(System.getProperty("user.dir")+"/src/main/webapp/audio/"+fileName);
        chapter.setId(UUID.randomUUID().toString().replace("-",""));
        chapter.setUrl(fileName);
        chapterService.add(chapter);
        file.transferTo(file1);
    }

    @GetMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = UUID.randomUUID().toString().replace("-","")+"112.mp3";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File(System.getProperty("user.dir")+"/src/main/webapp/audio/"+fileName);
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }



}
