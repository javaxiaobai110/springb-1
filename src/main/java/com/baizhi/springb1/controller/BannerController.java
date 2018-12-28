package com.baizhi.springb1.controller;

import com.baizhi.springb1.entity.Banner;
import com.baizhi.springb1.entity.DtoBanner;
import com.baizhi.springb1.excp.BannerUploadException;
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
    public String add(Banner banner, @RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        if(!file.getOriginalFilename().toLowerCase().endsWith("png") && !file.getOriginalFilename().toLowerCase().endsWith("jpg") && !file.getOriginalFilename().toLowerCase().endsWith("bmp") && !file.getOriginalFilename().toLowerCase().endsWith("gif"))
            throw new BannerUploadException("图片类型格式错误");
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getInputStream().available(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        banner.setImgPath(env.getProperty("fdfs.web-server-url")+"/"+storePath.getFullPath());
        bannerService.add(banner);
        return "添加成功";
    }

    @RequestMapping("/delete")
    public void delete(Integer id){
        //log.info(id+"&&&&&&&&&");
        bannerService.delete(id);
    }

}
