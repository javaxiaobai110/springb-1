package com.baizhi.springb1.excp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobleException {

    /*@ExceptionHandler(Exception.class)
    public Map<String, String> check(Exception e) {
        Map<String, String> map = new HashMap<>();
        if(e instanceof AdminException){
            map.put(((AdminException) e).getErrCode(),((AdminException) e).getErrMsg());
            return map;
        }
        return map;
    }*/
    @ExceptionHandler(AdminException.class)
    public String check(AdminException e) {
        return e.getMessage();
    }
}
