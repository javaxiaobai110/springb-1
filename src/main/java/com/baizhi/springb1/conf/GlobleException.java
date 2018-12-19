package com.baizhi.springb1.conf;


import com.baizhi.springb1.excp.AdminException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobleException {
    @ResponseBody
    @ExceptionHandler(AdminException.class)
    public String check(AdminException e) {
        return e.getMessage();
    }

}