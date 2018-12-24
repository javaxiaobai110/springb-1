package com.baizhi.springb1.controller;

import com.baizhi.springb1.conf.UploadUtils;
import com.baizhi.springb1.entity.Chapter;
import com.baizhi.springb1.excp.AddChapterException;
import com.baizhi.springb1.excp.UploadException;
import com.baizhi.springb1.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
@RequestMapping("chapter")
@Slf4j
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private Environment env;

    @RequestMapping("add")
    @ResponseBody
    public void add(Chapter chapter, @RequestParam("file") MultipartFile file) {
        //判断文件类型
        if (!file.getOriginalFilename().endsWith("mp3"))
            throw new AddChapterException("上传文件格式错误");

        //文件初始化
        String fileName = UploadUtils.getFileName(file.getOriginalFilename());
        File file1 = new File(System.getProperty("user.dir") + "/src/main/webapp/audio/" + fileName);

        //文件上传
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            throw new UploadException(file1.getAbsolutePath());
        }

        //获取上传文件信息并和指定文件信息比较
        Encoder encoder = new Encoder();
        MultimediaInfo m = null;
        try {
            m = encoder.getInfo(file1);
        } catch (EncoderException e) {
            throw new AddChapterException("获取文件信息错误");
        }
        long duration = m.getDuration();
        long a = duration / 1000;
        String property = env.getProperty("spring.http.multipart.max-file-size");
        String numbers = UploadUtils.getNumbers(property);
        long i = Long.parseLong(numbers);
        long length = file1.length();
        if (length > i * 1000 * 1000) {
            throw new AddChapterException("上传文件大于指定上传文件大小");
        }
        //插入章节信息到数据库
        chapter.setId(UUID.randomUUID().toString().replace("-", ""));
        chapter.setUrl(fileName);
        chapter.setDuration(a + "秒");
        chapter.setSize(file1.length() + "字节");

        try {
            chapterService.add(chapter);
        } catch (Exception e) {
            throw new AddChapterException(e.getMessage());
        }
        log.info("上传成功");
        return;
    }

    @RequestMapping("/download")
    public void download(String filename, HttpServletResponse response, HttpSession session, HttpServletRequest request) throws IOException {
        String path = session.getServletContext().getRealPath("/audio");
        String fileUrl = path+File.separatorChar+filename;
        File file = new File(fileUrl);
        filename = URLEncoder.encode(filename, "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + filename);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        ServletOutputStream outputStream1 = response.getOutputStream();
        outputStream1.write(bytes);
        outputStream1.close();
    }




    /*FileInputStream in = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();

        byte buffer[] = new byte[1024];         //创建缓冲区
        int len = 0;
        while ((len = in.read(buffer))>0){
            outputStream.write(buffer, 0, len);
        }
        in.close();*/

    /*public String processFileName(HttpServletRequest request, String fileNames){
        String codedFilename = null;
        try{
            String agent = request.getHeader("USER-AGENT");
            if(null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 !=agent.indexOf("Trident")){  //IE
                String name = java.net.URLEncoder.encode(fileNames, "UTF-8");

                codedFilename = name;
            }else if(null != agent && -1 != agent.indexOf("Mozilla")){  //火狐，Chrome等
                codedFilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return codedFilename;
    }*/




}
