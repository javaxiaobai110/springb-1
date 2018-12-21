package com.baizhi.springb1.controller;

import com.baizhi.springb1.conf.UploadUtils;
import com.baizhi.springb1.entity.Banner;
import com.baizhi.springb1.entity.DtoBanner;
import com.baizhi.springb1.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("banner")
@Slf4j
public class BannerController {

    @Autowired
    private BannerService bannerService;

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
        String fileName = UploadUtils.getFileName(file.getOriginalFilename());
        File file1 = new File(System.getProperty("user.dir")+"/src/main/webapp/img/"+fileName);
        banner.setImgPath(fileName);
        bannerService.add(banner);
        file.transferTo(file1);
    }

    @RequestMapping("/delete")
    public void delete(Integer id){
        //log.info(id+"&&&&&&&&&");
        bannerService.delete(id);
    }

}
