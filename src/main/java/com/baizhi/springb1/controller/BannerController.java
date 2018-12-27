package com.baizhi.springb1.controller;

import com.baizhi.springb1.entity.Banner;
import com.baizhi.springb1.entity.DtoBanner;
import com.baizhi.springb1.service.BannerService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("banner")
@Slf4j
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private Environment env;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @RequestMapping("showAll")
    public DtoBanner showAll(int page, int rows){
        DtoBanner dtoBanner = bannerService.findByPage(page, rows);
        return dtoBanner;
    }

    @RequestMapping("update")
    public void update(Integer id,Integer status){
        Banner oneById = bannerService.findOneById(id);
        oneById.setStatus(status);
        bannerService.update(oneById);
        return;
    }

    @RequestMapping(value="add",method = RequestMethod.POST)
    public void add(Banner banner, @RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        log.info(file.getOriginalFilename()+"文件名============");
        log.info(FilenameUtils.getExtension(file.getOriginalFilename())+"文件名扩展名============");
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getInputStream().available(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        String fullPath = storePath.getFullPath();
        //String group = storePath.getGroup();
        //String path = storePath.getPath();
        banner.setImgPath(env.getProperty("fdfs.web-server-url")+"/"+fullPath);
        bannerService.add(banner);
        //log.info(fullPath+"=============");
        //log.info(group+"=============");
        //log.info(path+"=============");
        //log.info(storePath.toString());
        //FileInfo fileInfo = fastFileStorageClient.queryFileInfo(group, path);
        //log.info(fileInfo.toString()+"==============++");
        //String sourceIpAddr = fileInfo.getSourceIpAddr();//没有端口
        //log.info(sourceIpAddr+"============-----");

        //fastFileStorageClient.queryFileInfo()
        //String fileName = UploadUtils.getFileName(file.getOriginalFilename());
        //String originalFilename = file.getOriginalFilename();
        //File file1 = new File(originalFilename);
        //File file1 = new File(System.getProperty("user.dir")+"/src/main/webapp/img/"+fileName);
        //banner.setImgPath(fileName);
        //ServletInputStream inputStream = request.getInputStream();
        //fastFileStorageClient.uploadFile(inputStream);
        //File file = new File("F:/呵呵.jpg");
        //StorePath storePath = fastFileStorageClient.uploadFile(, file.length(), FilenameUtils.getExtension(file.getName()), null);
        //bannerService.add(banner);
        //file.transferTo(file1);
    }

    @RequestMapping("/delete")
    public void delete(Integer id){
        //log.info(id+"&&&&&&&&&");
        bannerService.delete(id);
    }

}
