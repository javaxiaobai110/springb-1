package com.baizhi.springb1.controller;

import com.baizhi.springb1.conf.UploadUtils;
import com.baizhi.springb1.entity.Album;
import com.baizhi.springb1.entity.DtoAlbum;
import com.baizhi.springb1.service.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("album")
@Slf4j
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("showAll")
    public DtoAlbum showAll(int page, int rows){
        DtoAlbum all = albumService.findAll(page, rows);
        return all;
    }

    @RequestMapping("showOne")
    public DtoAlbum showOne(Integer id){
        return null;
    }

    @RequestMapping(value="add",method = RequestMethod.POST)
    public void add(Album album, @RequestParam("file") MultipartFile file) throws IOException {
        log.info(album+"============"+file.getOriginalFilename());
        String fileName = UploadUtils.getFileName(file.getOriginalFilename());
        File file1 = new File(System.getProperty("user.dir")+"/src/main/webapp/img/"+fileName);
        album.setCoverImg(fileName);
        albumService.add(album);
        file.transferTo(file1);

    }

}
