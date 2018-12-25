package com.baizhi.springb1.controller;

import com.baizhi.springb1.conf.FileUtil;
import com.baizhi.springb1.conf.UploadUtils;
import com.baizhi.springb1.entity.Album;
import com.baizhi.springb1.entity.DtoAlbum;
import com.baizhi.springb1.excp.ExportException;
import com.baizhi.springb1.service.AlbumService;
import lombok.extern.slf4j.Slf4j;
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
import java.io.UnsupportedEncodingException;
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
        try {
            String encode = URLEncoder.encode("呵呵.xls", "UTF-8");
            response.setHeader("content-disposition","attachment;filename="+encode);
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            FileUtil.exportExcel(albums,"音频分类","专辑音频",Album.class,"呵呵.xls",response);
        } catch (UnsupportedEncodingException e) {
            throw new ExportException("导出失败");
        }

        /*Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("音频", "专辑音频"),
                Album.class, albums);
        try {
            String encode = URLEncoder.encode("呵呵.xls", "UTF-8");
            response.setHeader("content-disposition","attachment;filename="+encode);
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new ExportException("导出失败");
        }
        return;*/
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
