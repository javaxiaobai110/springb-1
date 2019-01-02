package com.baizhi.springb1.excp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobleException {


    @ExceptionHandler(AdminException.class)
    public String check1(AdminException e) {
        return e.getMessage();
    }

    @ExceptionHandler({ChapterException.class})
    public String check2(ChapterException e1) {
        return e1.getMessage();
    }

    @ExceptionHandler(UploadException.class)
    public String check3(UploadException e) {
        File f = new File(e.getMessage());
        if(f.exists())
            f.delete();
        return "文件上传失败";
    }

    @ExceptionHandler(ExportException.class)
    public String check4(ExportException e) {
        return e.getMessage();
    }

    @ExceptionHandler(AlbumException.class)
    public Object check5(AlbumException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("202",e.getMessage());
        return map;
    }

    @ExceptionHandler(UserException.class)
    public Object check6(UserException e){
        if (e.getClass() == PhoneCheckCodeException.class){
            Map<String, Object> map = new HashMap<>();
            map.put("resutl","fail");
            return map;
        }else {
            Map<String,Object> map = new HashMap<>();
            map.put("error","-200");
            map.put("error_msg",e.getMessage());
            return map;
        }
    }

    @ExceptionHandler(BannerException.class)
    public String check7(BannerException e){
        return e.getMessage();
    }

    @ExceptionHandler(ArticleException.class)
    public Object check8(ArticleException e){
        Map<String,Object> map = new HashMap<>();
        map.put("500",e.getMessage());
        return map;
    }
}
