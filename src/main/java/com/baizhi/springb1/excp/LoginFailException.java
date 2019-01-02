package com.baizhi.springb1.excp;

public class LoginFailException extends UserException {
    public LoginFailException(){}
    public LoginFailException(String msg){super(msg);}
}
