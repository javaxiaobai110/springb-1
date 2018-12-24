package com.baizhi.springb1.excp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.File;

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
    public String check5(AlbumException e) {
        return e.getMessage();
    }
}
