package com.baizhi.springb1.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.springb1.conf.UploadUtils;
import com.baizhi.springb1.entity.Album;
import com.baizhi.springb1.entity.DtoAlbum;
import com.baizhi.springb1.excp.ExportException;
import com.baizhi.springb1.service.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("album")
@Slf4j
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private Environment env;

    @RequestMapping("exportAllAlbum")
    public void exportAllAlbum(HttpServletResponse response){

        //response.setContentType("application/ms-excel;charset=utf-8");
        //response.setHeader("content-Disposition", "attachment;filename="+processFileName(request,filename));




        List<Album> albums = albumService.exportAll();
        for (Album album : albums) {
            String realPath = env.getProperty("file.real.path");
            log.info(realPath+File.separatorChar+album.getCoverImg()+"----------------");
            album.setCoverImg(realPath+File.separatorChar+album.getCoverImg());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("音频", "专辑音频"),
                Album.class, albums);
        try {
            //workbook.write(new FileOutputStream(new File("F:/easypoi.xls")));
            String encode = URLEncoder.encode("呵呵.xls", "UTF-8");
            response.setHeader("content-disposition","attachment;filename="+encode);
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new ExportException("导出失败");
        }
        return;
    }

    @RequestMapping("showAll")
    @ResponseBody
    public DtoAlbum showAll(int page, int rows){
        DtoAlbum all = albumService.findAll(page, rows);
        return all;
    }

    @RequestMapping("showOne")
    @ResponseBody
    public DtoAlbum showOne(Integer id){
        return null;
    }

    @RequestMapping(value="add",method = RequestMethod.POST)
    @ResponseBody
    public void add(Album album, @RequestParam("file") MultipartFile file) throws IOException {
        log.info(album+"============"+file.getOriginalFilename());
        String fileName = UploadUtils.getFileName(file.getOriginalFilename());
        File file1 = new File(System.getProperty("user.dir")+"/src/main/webapp/img/"+fileName);
        album.setCoverImg(fileName);
        albumService.add(album);
        file.transferTo(file1);
    }
}
